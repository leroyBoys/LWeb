package com.lweb.service;

import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.MainData;
import com.lweb.entity.QueryPage;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/7/3.
 */
public interface WebMainService {
    QueryPage getBaseMains(QueryPage page);
    Main getMainById(int id);
    void saveMain(Main main);
    void saveMainData(MainData mainData);
    void delMainData(int mainDataId);
    void clearMainData(int mainId);
}
