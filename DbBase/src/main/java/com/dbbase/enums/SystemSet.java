package com.dbbase.enums;

import com.lqsmart.mysql.entity.LQDBEnum;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public enum SystemSet implements LQDBEnum<Integer> {
    WebName(0),WebLocation(1),
    /** 主页分页大小 */
    DefaultMainPageSize(2);
    private final int type;
    SystemSet(int type){
        this.type = type;
    }

    @Override
    public Integer getDBValue() {
        return type;
    }
}
