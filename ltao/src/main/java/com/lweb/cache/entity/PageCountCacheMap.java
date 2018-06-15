package com.lweb.cache.entity;

import com.lgame.util.PrintTool;
import com.lgame.util.comm.StringTool;
import com.lqsmart.util.LRUMap;
import com.lweb.manager.TimeCacheManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/15.
 */
public class PageCountCacheMap extends LRUMap<String,Integer> {
    private final long expreTime;
    private Map<String,Long> keyExpreTimeMap = new HashMap<>();

    private ReadWriteLock writeLock = new ReentrantReadWriteLock();
    private final Lock r = writeLock.readLock();
    private final Lock w = writeLock.writeLock();
    /**
     *
     * @param maxSize 最大数量
     * @param expreTime 有效期(秒)
     */
    public PageCountCacheMap(int maxSize,int expreTime) {
        super(maxSize);
        this.expreTime = expreTime*1000;
    }

    @Override
    public Integer put(String key, Integer value) {
        w.lock();
        try {
            keyExpreTimeMap.put(key, TimeCacheManager.instance().getCurTime()+expreTime);
            return super.put(key, value);
        }finally {
            w.unlock();
        }
    }

    @Override
    public Integer remove(Object key) {
        w.lock();
        try {
            keyExpreTimeMap.remove(key);
            return super.remove(key);
        }finally {
            w.unlock();
        }
    }

    @Override
    public Integer get(Object key) {
        r.lock();
        try {
            Long ll = keyExpreTimeMap.get(key);
            if(ll == null){
                return null;
            }

            if(ll.longValue() > TimeCacheManager.instance().getCurTime()){
                return super.get(key);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            r.unlock();
        }

        remove(key);
        return null;
    }

}
