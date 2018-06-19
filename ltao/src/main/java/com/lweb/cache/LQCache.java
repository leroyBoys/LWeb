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
public class LQCache extends LQCacheSuper<String>{
    private static final LQCache intance = new LQCache();
    protected LQCache(){}

    private final Map<String,Object> cacheMap = new HashMap<>();

    public static LQCache getIntance(){
        return intance;
    }

    protected Node<LQRedisConnection> getRedisConnection(){
        return LQStart.getRedisConnectionManager().getNode();
    }

    protected Node<LQDataSource> getDataSourceConnection(){
        return LQStart.getJdbcManager().getNode();
    }


    protected String merageKey(String prexKey,Object[] parater){
        if(parater != null && parater.length>0){
            for(int i = 0,size=parater.length;i<size;i++){
                prexKey+="."+parater[i];
            }
        }
        return prexKey;
    }

    protected String cacheKey(LQCacheKey lqCacheKey, Object[] parater){
        return merageKey(lqCacheKey.getPrexKey(),parater);
    }

    protected Map<String,Object> getCacheMap(){
        return cacheMap;
    }

}
