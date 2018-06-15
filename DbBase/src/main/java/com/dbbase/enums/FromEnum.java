package com.dbbase.enums;

import com.lqsmart.mysql.entity.LQDBEnum;

/**
 * 来源:本地(0-9)/淘宝(10-19)/京东（20-29）
 * Created by leroy:656515489@qq.com
 * 2018/6/11.
 */
public enum FromEnum implements LQDBEnum<Integer>{
    self(0),taobao(10);
    private final int type;
    FromEnum(int type){
        this.type = type;
    }

    @Override
    public Integer getDBValue() {
        return type;
    }

}
