package com.lweb.action;

import com.dbbase.enums.PlatformType;
import com.lweb.cache.LQCache;
import com.lweb.cache.LQCacheKey;
import com.lweb.cache.LQPageCache;
import com.lweb.manager.ErrorCode;
import com.lweb.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@RestController
@RequestMapping("/")
public class IndexAction {
    @Autowired
    private SystemService systemService;

    @RequestMapping
    String home() {
        return "Hello World!";
    }

    @RequestMapping("wx")
    Object pcHome(Integer page) {
        LQCache lqCache = LQCache.getIntance();

        if(page ==null){
            Object id = lqCache.getCache(LQCacheKey.DefaultMainID, PlatformType.weixin);
            if(id == null){
                return ErrorCode.ParamterError;
            }

            Object object= LQCache.getIntance().getCache(LQCacheKey.Main, id);
            return object;
        }
        return LQPageCache.getIntance().getPageResult("",page);
    }
}
