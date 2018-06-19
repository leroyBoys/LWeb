package com.dbbase.moudle.system;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

/**
 * 主页数据
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@LQDBTable(name = "system_main_data")
@RedisCache
public class MainData {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private int main_id;
    @LQField
    private int startTime;
    @LQField
    private int endTime;
    @LQField
    private String data;

    @LQField(name = "tmp_type")
    private short tmpType;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public int getMain_id() {
        return main_id;
    }

    public short getTmpType() {
        return tmpType;
    }

    public void setTmpType(short tmpType) {
        this.tmpType = tmpType;
    }

    public void setMain_id(int main_id) {
        this.main_id = main_id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setData(String data) {
        this.data = data;
    }
}
