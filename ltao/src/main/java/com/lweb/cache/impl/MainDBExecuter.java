package com.lweb.cache.impl;

import com.dbbase.moudle.system.Main;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;
import com.lweb.cache.LQSystemCache;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class MainDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object... parater) {
        int id = (int) parater[0];
        int pageSize = LQSystemCache.getIntance().getDefaultMainPageSize();
        return dataSource.ExecuteQueryOne(Main.class,"SELECT m.id,m.title,m.tmpId,d.data FROM `system_main` m LEFT JOIN `system_main_data` d ON  d.`main_id`= m.id  WHERE m.id = ?  LIMIT 0,?",id,pageSize);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
