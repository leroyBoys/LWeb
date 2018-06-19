package com.lweb.cache.impl;

import com.dbbase.moudle.system.Main;
import com.lqsmart.mysql.impl.LQDataSource;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class MainDBExecuter extends DefalutMainDBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        int id = (int) parater[0];
        Main main = dataSource.ExecuteQueryOne(Main.class,"SELECT * FROM `system_main` WHERE id="+id);
        return getMain(main);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
