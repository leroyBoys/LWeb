package com.lweb.moudle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public class SessionContainer {
    public final static String key = "sc_sk";
    public final static int maxRequestingCount = 10;
    private AtomicInteger requestingCount = new AtomicInteger(0);
    private BaseUserInfo userInfo;

    public int addRequest(){
        return requestingCount.incrementAndGet();
    }

    public int removeRequest(){
        return requestingCount.decrementAndGet();
    }

    public BaseUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BaseUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
