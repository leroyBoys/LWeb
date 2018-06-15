package com.dbbase.enums;

import com.lqsmart.mysql.entity.LQDBEnum;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public enum MainType implements LQDBEnum<Integer> {
    /** 首页 */
    Main(0),
    /** 搜索 */
    Search(1),
    /** 文章 **/
    Article(2),
    /** 主页分页大小 */
    DefaultMainPageSize(2);
    private final int type;
    MainType(int type){
        this.type = type;
    }

    @Override
    public Integer getDBValue() {
        return type;
    }
}
