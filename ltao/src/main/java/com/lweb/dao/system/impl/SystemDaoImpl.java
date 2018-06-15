package com.lweb.dao.system.impl;

import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.Section;
import com.dbbase.enums.PlatformType;
import com.lweb.dao.system.SystemDao;
import org.springframework.stereotype.Component;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@Component
public class SystemDaoImpl implements SystemDao{

    @Override
    public Main getMainData(int id) {
        if(id == 0){
            return daoManager.getRandomSlave().ExecuteQueryOne(Main.class,"select * from system_main_data where   type=?", PlatformType.pc);
        }
        return daoManager.getRandomSlave().ExecuteQueryOne(Main.class,id);
    }

    @Override
    public void SaveMainData(Main mainData) {
        daoManager.getMaster().ExecuteEntity(mainData);
    }

    @Override
    public void SaveSection(Section section) {
        daoManager.getMaster().ExecuteEntity(section);
    }

    @Override
    public Section getSection(int sectionId) {
        return daoManager.getRandomSlave().ExecuteQueryOne(Section.class,sectionId);
    }

}
