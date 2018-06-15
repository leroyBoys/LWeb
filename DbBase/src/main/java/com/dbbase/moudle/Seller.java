package com.dbbase.moudle;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@LQDBTable(name = "seller")
public class Seller {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String name;
    @LQField
    private short from;
    @LQField
    private int sid;
    @LQField
    private String contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getFrom() {
        return from;
    }

    public void setFrom(short from) {
        this.from = from;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
