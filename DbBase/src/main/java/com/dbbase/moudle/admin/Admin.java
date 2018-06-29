package com.dbbase.moudle.admin;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
@LQDBTable(name = "system_admin")
public class Admin {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String name;
    @LQField
    private String password;
    @LQField
    private String image;

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

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
