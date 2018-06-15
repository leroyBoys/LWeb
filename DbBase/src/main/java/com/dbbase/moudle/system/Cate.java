package com.dbbase.moudle.system;

import com.lqsmart.mysql.entity.DBRelation;
import com.lqsmart.mysql.entity.DBRelations;
import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/11.
 */
@RedisCache
@LQDBTable(name="system_cate")
public class Cate {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String name;
    @LQField
    private String desc;

    @DBRelations(relation=DBRelations.Reltaion.OneToMany,map = {
            @DBRelation(colum = "cate_id_taobao",targetColum = "cate_id_taobao")
    })
    private List<Integer> taoBaoCateIds;
    public int getId() {
        return id;
    }

    public List<Integer> getTaoBaoCateIds() {
        return taoBaoCateIds;
    }

    public void setTaoBaoCateIds(List<Integer> taoBaoCateIds) {
        this.taoBaoCateIds = taoBaoCateIds;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
