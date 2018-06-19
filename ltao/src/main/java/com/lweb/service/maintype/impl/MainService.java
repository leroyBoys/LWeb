package com.lweb.service.maintype.impl;

import com.dbbase.moudle.system.MainData;
import com.lweb.cache.LQPageCache;
import com.lweb.service.maintype.MainTypeService;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
public class MainService implements MainTypeService<MainData> {
    @Override
    public List<MainData> queryResults(int id, int page) {
        return LQPageCache.getIntance().getMainPages(id,page);
    }
}
