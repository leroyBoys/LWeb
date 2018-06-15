package com.lweb.dao.system;

import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.MainData;
import com.dbbase.moudle.system.Section;
import org.springframework.stereotype.Component;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
public interface SystemDao extends SuperDao{
    Main getMainData(int id);
    void SaveMainData(Main mainData);
    void SaveSection(Section section);
    Section getSection(int sectionId);
}
