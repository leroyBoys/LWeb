package com.dbbase.moudle.system;

import com.lqsmart.mysql.entity.DBRelation;
import com.lqsmart.mysql.entity.DBRelations;
import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

import java.util.List;

/**
 * 板块区域
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@LQDBTable(name = "system_section")
@RedisCache
public class Section {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String name;
    /**
     * 备注
     */
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
    @LQField
    private boolean open;

    @DBRelations(relation = DBRelations.Reltaion.OneToMany,map = {
            @DBRelation(colum = "cid")
    })
    private List<Integer> cateIds;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWeiUrl() {
        return weiUrl;
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Integer> getCateIds() {
        return cateIds;
    }

    public void setCateIds(List<Integer> cateIds) {
        this.cateIds = cateIds;
    }
}
