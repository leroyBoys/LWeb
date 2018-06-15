package com.lweb.cache;

import com.dbbase.enums.SystemSet;
import com.lweb.cache.entity.LQCacheKey;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class LQSystemCache {
    private static final LQSystemCache intance = new LQSystemCache();
    protected LQSystemCache(){
    }

    public static final LQSystemCache getIntance(){
        return intance;
    }

    public final String getSet(SystemSet systemSet){
        Object obj = LQCache.getIntance().getCache(LQCacheKey.SystemSet,systemSet);
        return obj == null?null:(String)obj;
    }

    /**
     *  默认 主页 分页pagesize大小
     * @return
     */
    public int getDefaultMainPageSize() {
        String pageSize = getSet(SystemSet.DefaultMainPageSize);
        return pageSize == null?2:Integer.valueOf(pageSize);
    }
}
