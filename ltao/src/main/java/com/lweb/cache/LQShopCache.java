package com.lweb.cache;

import com.dbbase.moudle.Shop;
import com.lqsmart.util.LRUMap;
import com.lweb.cache.entity.LQCacheKey;

/**
 * 分页cache
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public class LQShopCache extends LQCacheSuper<Long>{
    private static final LQShopCache intance = new LQShopCache();
    /** 缓存最大页数 */
    private final int maxContainShopCount = 20;
    private LQShopCache() {
    }
    public static LQShopCache getIntance(){
        return intance;
    }

    private LRUMap<Long,Shop> cacheMap = new LRUMap(maxContainShopCount);

    @Override
    protected Long cacheKey(LQCacheKey lqCacheKey, Object[] parater) {
        return (Long) parater[0];
    }

    @Override
    public LRUMap getCacheMap() {
        return cacheMap;
    }

    @Override
    protected Object getDataFromDB(LQCacheKey lqCacheKey, Object[] parater) {
        return getDataFromMysql(lqCacheKey,parater);
    }



}
