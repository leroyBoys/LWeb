package com.lweb.dao.system;

import com.lqsmart.core.LQStart;
import com.lqsmart.mysql.impl.JDBCManager;
import com.lqsmart.redis.impl.RedisConnectionManager;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
public interface SuperDao {
    JDBCManager daoManager = LQStart.getJdbcManager();
    RedisConnectionManager redisManager = LQStart.getRedisConnectionManager();
}
