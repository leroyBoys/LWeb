package com.lweb.cache.impl;

import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class DefalutMainIdDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        int type = (int) parater[0];
        return dataSource.ExecuteQueryOnlyOneValue("SELECT id FROM `system_main` WHERE `type`= ? AND isDefault = TRUE",new Object[]{type});
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
