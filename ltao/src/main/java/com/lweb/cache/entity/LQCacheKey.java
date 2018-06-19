package com.lweb.cache.entity;

import com.dbbase.moudle.Shop;
import com.dbbase.moudle.system.Main;
import com.lqsmart.redis.entity.RedisExecuter;
import com.lqsmart.redis.entity.RedisKey;
import com.lweb.cache.DBExecuter;
import com.lweb.cache.impl.*;
import com.lweb.manager.TimeCacheManager;

/**
 * 存取路径 cache--mysql
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public enum LQCacheKey implements RedisKey{
    SystemSet("systemSet",0, RedisExecuter.strings(),new SystemSetDBExecuter()),
    /** 默认主页id  */
    DefaultMain("defaultMain",TimeCacheManager.DAY/1000,null,new DefalutMainDBExecuter()),
    /** 主页  */
    Main("main",TimeCacheManager.DAY/1000,RedisExecuter.bytes(Main.class),new MainDBExecuter()),
    Page("page",TimeCacheManager.DAY/1000,null,new PageDBExecuter()),
    Seciton("section",TimeCacheManager.DAY/1000,null,new SecitonDBExecuter()),
    ShopDetail("shop", 5*TimeCacheManager.MINUTE/1000,RedisExecuter.bytes(Shop.class) ,new ShopDetailDbExecuter());
    private final String prexKey;
    /** 有效期(秒,0没有有效期) */
    private int expire;
    /**
     *  序列化 器（如果不存redis，则可以不填写）
     */
    private final RedisExecuter redisExecuter;
    private final DBExecuter dbExecuter;//mysql 更新 接口，如果没有或者null则不向mysql发出信息

    LQCacheKey(String prexKey,int expire,RedisExecuter redisExecuter,DBExecuter dbExecuter) {
        this.prexKey = prexKey;
        this.expire = expire;
        this.redisExecuter = redisExecuter;
        this.dbExecuter = dbExecuter;
    }

    @Override
    public RedisExecuter getSerialzer() {
        return redisExecuter;
    }

    @Override
    public String getPrexKey() {
        return prexKey;
    }

    public DBExecuter getDBExecuter() {
        return dbExecuter;
    }

    @Override
    public int getExpire() {
        return expire;
    }
}
