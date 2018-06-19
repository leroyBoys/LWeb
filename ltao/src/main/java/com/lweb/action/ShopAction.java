package com.lweb.action;

import com.lweb.cache.LQShopCache;
import com.lweb.cache.entity.LQCacheKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@RestController
@RequestMapping("/shop")
public class ShopAction {
    @RequestMapping
    Object getShopDetail(int id) {
        return LQShopCache.getIntance().getCache(LQCacheKey.ShopDetail,id);
    }

}
