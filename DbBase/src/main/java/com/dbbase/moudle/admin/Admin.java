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
    @LQField
    private String real_name;
    @LQField
    private String phone;
    @LQField
    private String id_card;
    @LQField
    private byte sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPhone() {
        return phone;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
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
