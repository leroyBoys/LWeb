package com.dbbase.moudle.system;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

/**
 *  显示菜单
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@LQDBTable(name="system_menu")
@RedisCache
public class Menu {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String name;
    @LQField
    private String note;
    @LQField(name = "wei_url")
    private String weiUrl;
    @LQField(name = "url")
    private String url;
    @LQField(name = "icon")
    private String icon;
    @LQField(name = "wei_icon")
    private String weiIcon;
    @LQField(name = "parent_id")
    private int parentId;
    @LQField
    private boolean open;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWeiUrl() {
        return weiUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeiUrl(String weiUrl) {
        this.weiUrl = weiUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWeiIcon() {
        return weiIcon;
    }

    public void setWeiIcon(String weiIcon) {
        this.weiIcon = weiIcon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
