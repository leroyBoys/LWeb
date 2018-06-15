package com.dbbase.moudle.system;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;
import com.lqsmart.redis.entity.RedisCache;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@RedisCache(keyMethodName = "getCateIdFormTaobao")
@LQDBTable(name = "system_cate_map_taobao")
public class CateMapData {
    @LQField(name = "cate_id")
    private int cateId;
    @LQField(name = "cate_id_taobao",isPrimaryKey = true)
    private int cateIdFormTaobao;

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getCateIdFormTaobao() {
        return cateIdFormTaobao;
    }

    public void setCateIdFormTaobao(int cateIdFormTaobao) {
        this.cateIdFormTaobao = cateIdFormTaobao;
    }
}
