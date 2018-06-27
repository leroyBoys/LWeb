package com.lweb.entity;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/20.
 */
@LQDBTable(name = "system_manager_menu")
public class SystemMenu {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField(name = "title")
    private String text;
    @LQField
    private String url;
    @LQField
    private int parentId;
    @LQField
    private String icon;

    private List<SystemMenu>  children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParentId() {
        return parentId;
    }

    public List<SystemMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SystemMenu> children) {
        this.children = children;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
