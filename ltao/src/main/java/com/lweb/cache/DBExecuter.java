package com.lweb.cache;

import com.lqsmart.mysql.impl.LQDataSource;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public abstract class DBExecuter {
    /**
     *  从数据库中查询
     * @param dataSource
     * @param parater
     * @return
     */
    public abstract Object getDataFromDB(LQDataSource dataSource, Object[] parater);

    /**
     *  向mysql中更新缓存对象
     * @param master
     * @param cache
     * @return
     */
    public abstract boolean updateDb(LQDataSource master, Object cache);
}
