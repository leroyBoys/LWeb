package com.dbbase.enums;

import com.lqsmart.mysql.entity.LQDBEnum;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public enum PlatformType implements LQDBEnum<Integer> {
    pc(0),weixin(1);
    private final int type;
    PlatformType(int type){
        this.type = type;
    }

    @Override
    public Integer getDBValue() {
        return type;
    }

    public int getType() {
        return type;
    }
}