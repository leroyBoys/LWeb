package com.lweb.cache.impl;

import com.dbbase.enums.SystemSet;
import com.dbbase.moudle.system.Main;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class SystemSetDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object... parater) {
        int id = ((SystemSet)parater[0]).getDBValue();
        return dataSource.ExecuteQueryOne(Main.class,"SELECT `value` FROM `system_setting` WHERE id = ?",id);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
