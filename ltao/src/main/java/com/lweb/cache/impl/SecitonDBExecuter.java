package com.lweb.cache.impl;

import com.dbbase.moudle.system.Section;
import com.lqsmart.core.LQStart;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;
import com.lweb.cache.entity.PageDetail;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class SecitonDBExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        int id = (int) parater[0];
        return dataSource.ExecuteQueryOne(Section.class,"SELECT * FROM `system_section` LEFT JOIN `system_section_cate` ON sid = id WHERE id = "+id);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
