package com.lweb.service.maintype.impl;

import com.dbbase.moudle.Shop;
import com.lweb.cache.LQPageCache;
import com.lweb.service.maintype.MainTypeService;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
public class SearchService implements MainTypeService<Shop> {
    @Override
    public List<Shop> queryResults(int id, int page) {
        return LQPageCache.getIntance().getSearchShopPages(id,page);
    }
}
