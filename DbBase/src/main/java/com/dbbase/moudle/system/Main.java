package com.dbbase.moudle.system;

import com.dbbase.enums.PlatformType;
import com.lqsmart.mysql.entity.DBRelation;
import com.lqsmart.mysql.entity.DBRelations;
import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

import java.util.List;

import static com.lqsmart.mysql.entity.LQField.ConvertDBType.EnumNumber;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
@LQDBTable(name = "system_main")
@RedisCache
public class Main {
    @LQField(isPrimaryKey = true)
    private int id;
    @LQField
    private String title;
    @LQField
    private String desc;
    @LQField
    private String tmpId;
    @LQField(convertDBType = EnumNumber)
    private PlatformType type;
    @LQField
    /**
     * 是否为默认主页
     */
    private boolean isDefault;

    @DBRelations(relation = DBRelations.Reltaion.OneToMany,
    map = {
            @DBRelation(colum = "data"),
            @DBRelation(colum = "startTime"),
            @DBRelation(colum = "endTime")
    })
    private List<MainData> mainDatas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<MainData> getMainDatas() {
        return mainDatas;
    }

    public void setMainDatas(List<MainData> mainDatas) {
        this.mainDatas = mainDatas;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getTmpId() {
        return tmpId;

    }

    public void setTmpId(String tmpId) {
        this.tmpId = tmpId;
    }

    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }
}
