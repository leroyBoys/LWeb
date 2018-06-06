package com.lweb.manager;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
public class TimeCacheManager {
    private static final TimeCacheManager timeCacheManager= new TimeCacheManager();
    private TimeCacheManager(){}
    private long curTime;

    public void setCurTime(long time){
        this.curTime = time;
    }

    public long getCurTime(){
        return curTime;
    }

    public long getCurTimeNoCache(){
        return System.currentTimeMillis();
    }

    public void refresh(){
        this.curTime = getCurTimeNoCache();
    }

    public static final TimeCacheManager instance(){
        return timeCacheManager;
    }
}
