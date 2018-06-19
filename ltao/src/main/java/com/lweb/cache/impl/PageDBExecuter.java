package com.lweb.cache.impl;

import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;
import com.lweb.cache.entity.PageDetail;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class PageDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        String sql = (String) parater[0];
        PageDetail pageDetail = (PageDetail) parater[1];

        Object[] pars = null;
        if(parater.length > 2){
            pars = (Object[]) parater[2];
        }
        return dataSource.ExecuteQueryList(pageDetail.getT(),sql, pars);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
