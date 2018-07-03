package com.lweb.service.impl;

import com.dbbase.moudle.admin.Admin;
import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.MainData;
import com.lgame.util.encry.MD5Tool;
import com.lqsmart.core.LQStart;
import com.lweb.service.WebMainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/7/3.
 */
@Service
public class WebMainServiceImpl implements WebMainService{
    @Override
    public List<Main> getBaseMains() {
        return LQStart.getJdbcManager().getMaster().ExecuteQueryList(Main.class,"select * from system_main");
    }

    @Override
    public Main getMainById(int id) {
        return LQStart.getJdbcManager().getMaster().ExecuteQueryOne(Main.class,"SELECT system_main.*, system_main_data.`id` AS data_id, system_main_data.`data`, system_main_data.`sortNum`, system_main_data.`startTime`, system_main_data.`endTime`, system_main_data.`tmp_type` FROM `system_main` LEFT JOIN `system_main_data` ON  system_main_data.`main_id`= system_main.`id` WHERE system_main.`id` = "+id);
    }

    @Override
    public void saveMain(Main main) {
        LQStart.getJdbcManager().getMaster().ExecuteEntity(main);
    }

    @Override
    public void saveMainData(MainData mainData) {
        LQStart.getJdbcManager().getMaster().ExecuteEntity(mainData);
    }

    @Override
    public void delMainData(int mainDataId) {
        LQStart.getJdbcManager().getMaster().DelEntity(MainData.class,mainDataId);
    }

    @Override
    public void clearMainData(int mainId) {
        LQStart.getJdbcManager().getMaster().Execute("DELETE FROM `system_main_data`  WHERE main_id="+mainId,null);
    }
}
