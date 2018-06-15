package com.lweb.service.system.impl;

import com.lweb.dao.system.SystemDao;
import com.lweb.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Override
    public String getMainForWx() {
        return null;
    }

    @Override
    public String getMainDetailForWx(int max) {
        return null;
    }
}
