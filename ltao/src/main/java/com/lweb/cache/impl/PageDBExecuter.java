package com.lweb.cache.impl;

import com.dbbase.moudle.system.Main;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class PageDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object... parater) {
        String sql = (String) parater[0];
        Object[] pars = new Object[parater.length-1];
        System.arraycopy(parater,1,pars,0,pars.length);
        return dataSource.ExecuteQueryOne(Main.class,sql, pars);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
