package com.lweb.cache.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/15.
 */
public class PageDetail<T> {
    @JSONField(serialize =false)
    private int start;
    @JSONField(serialize =false)
    private int end;
    private int pageCurrent = 1;
    private int pageSize;
    private int count;
    private int maxPageNum;
    @JSONField(serialize =false)
    private Class<T> t;
    @JSONField(serialize =false)
    private List<T> results = new LinkedList();

    public PageDetail(Class<T> t,int pageCurrent, int pageSize) {
        this.t = t;
        this.setPageCurrent(pageCurrent);
        this.setPageSize(pageSize);
    }

    public List<T> getResults() {
        return this.results;
    }

    public int getPageCurrent() {
        return this.pageCurrent;
    }

    private void setPageCurrent(int current) {
        if(current > 0) {
            this.pageCurrent = current;
        } else {
            this.pageCurrent = 1;
        }

        if(this.pageSize > 0) {
            this.start = (this.pageCurrent - 1) * this.pageSize;
            this.end = this.pageCurrent * this.pageSize;
        }

    }

    private void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.start = (this.pageCurrent - 1) * pageSize;
        this.end = this.pageCurrent * pageSize;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
        maxPageNum = count%pageSize == 0?count/pageSize:((count/pageSize)+1);
    }

    public int getStart() {
        return this.start;
    }

    public int getMaxPageNum() {
        return maxPageNum;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Class<T> getT() {
        return t;
    }
    public int getEnd() {
        return this.end;
    }
}
