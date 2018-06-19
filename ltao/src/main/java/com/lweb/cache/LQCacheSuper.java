package com.lweb.cache;

import com.lqsmart.core.LQStart;
import com.lqsmart.entity.Node;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lqsmart.redis.impl.LQRedisConnection;
import com.lweb.cache.entity.LQCacheKey;

import java.util.HashMap;
import java.util.Map;

/**
 *  存取路径 cache--redis--mysql
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public abstract class LQCacheSuper<KEY> {
    protected Node<LQRedisConnection> getRedisConnection(){
        return LQStart.getRedisConnectionManager().getNode();
    }

    protected Node<LQDataSource> getDataSourceConnection(){
        return LQStart.getJdbcManager().getNode();
    }

    protected abstract KEY cacheKey(LQCacheKey lqCacheKey, Object[] parater);
    protected abstract Map<KEY,Object> getCacheMap();

    protected final Object getDataFromMysql(LQCacheKey lqCacheKey,Object[] parater){
        DBExecuter dbExecuter = lqCacheKey.getDBExecuter();
        if(dbExecuter == null){
            return null;
        }
        return dbExecuter.getDataFromDB(getDataSourceConnection().getRandomSlave(),parater);
    }

    protected Object getDataFromDB(LQCacheKey lqCacheKey,Object[] parater){
        if(lqCacheKey.getSerialzer() == null){
            return getDataFromMysql(lqCacheKey,parater);
        }

        Object object = getRedisConnection().getRandomSlave().getObject(lqCacheKey,parater);
        if(object == null){
            object = getDataFromMysql(lqCacheKey,parater);
            if(object == null){
                return null;
            }
        }

        getRedisConnection().getMaster().setObject(lqCacheKey,object,parater);
        return object;
    }

    public final <T> T getCache(LQCacheKey lqCacheKey,Object... parater){
        KEY cacheKey = cacheKey(lqCacheKey,parater);

        final Map<KEY,Object> _cacheMap = getCacheMap();
        Object object = _cacheMap.get(cacheKey);
        if(object != null) return (T) object;

        synchronized (lqCacheKey){
            object = _cacheMap.get(cacheKey);
            if(object != null) return (T) object;

           object = getDataFromDB(lqCacheKey,parater);
           if(object == null){
               return null;
           }
            _cacheMap.put(cacheKey,object);
        }

        return  (T)object;
    }

    public void updateCache(LQCacheKey lqCacheKey,Object cache,Object... parater){
        final DBExecuter dbExecuter = lqCacheKey.getDBExecuter();

        if(!dbExecuter.updateDb(getDataSourceConnection().getMaster(),cache)){
            return;
        }
    }
}
