package com.dbbase.enums;

import com.lqsmart.mysql.entity.LQDBEnum;

/**
 *  模板类型
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public enum TmpType implements LQDBEnum<Integer> {
    /** 单页广告 */
    ad(0),
    /** 轮播 */
    swiper(1),
    /** 主页模块 **/
    main_module(2);
    private final int type;
    TmpType(int type){
        this.type = type;
    }

    @Override
    public Integer getDBValue() {
        return type;
    }
}
