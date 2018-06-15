package com.lweb.cache;

import com.lqsmart.util.LRUMap;

import java.lang.ref.WeakReference;

/**
 * 分页cache
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public class LQPageCache extends LQCache{
    private static final LQPageCache intance = new LQPageCache();
    /** 缓存最大页数 */
    private final int maxPageCount = 20;
    /**  缓存最大结果集数量（即固定条件下总数据量为一个cache） */
    private final int maxPageResultCount = 500;
    private LQPageCache() {
    }
    public static LQPageCache getIntance(){
        return intance;
    }

    /** 暂时不用 */
    private LRUMap<String,Integer> pageResultCache = new LRUMap(maxPageResultCount);
    private WeakReference<LRUMap> cacheMap = new WeakReference<>(new LRUMap(maxPageCount));

    @Override
    public LRUMap getCacheMap() {
        LRUMap lruMap = cacheMap.get();
        if(lruMap == null){
            synchronized (this){
                lruMap = cacheMap.get();
                if(lruMap != null){
                    return lruMap;
                }

                lruMap = new LRUMap(maxPageCount);
                cacheMap =  new WeakReference<>(lruMap);
            }
        }

        return lruMap;
    }

    @Override
    protected Object getDataFromDB(LQCacheKey lqCacheKey, Object... parater) {
        return getDataFromMysql(lqCacheKey,parater);
    }

    public <T> T getPageResult(String sql,Object... parater){
        return getCache(LQCacheKey.Page,sql,parater);
    }
}
