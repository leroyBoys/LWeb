package com.lweb.service.system.impl;

import com.dbbase.enums.PlatformType;
import com.lweb.cache.LQCache;
import com.lweb.cache.LQPageCache;
import com.lweb.cache.entity.LQCacheKey;
import com.lweb.moudle.ErrorCode;
import com.lweb.moudle.ErrorResponse;
import com.lweb.service.system.IndexService;
import org.springframework.stereotype.Service;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/15.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public Object getDefaultMain(int platformType, int page) {
        LQCache lqCache = LQCache.getIntance();
        Object id = lqCache.getCache(LQCacheKey.DefaultMainID, platformType);
        if(id == null){
            return ErrorResponse.value(ErrorCode.ParamterError);
        }
        if(page ==1){
            Object object= LQCache.getIntance().getCache(LQCacheKey.Main, id);
            return object;
        }
        return LQPageCache.getIntance().getMainPages((int)id,page);
    }
}
