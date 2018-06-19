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

    public List<Integer> getCateIds() {
        return cateIds;
    }

    public void setCateIds(List<Integer> cateIds) {
        this.cateIds = cateIds;
    }
}
