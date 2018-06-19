package com.lweb.cache.impl;

import com.dbbase.log.LogUtil;
import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.Section;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;
import com.lweb.cache.LQCache;
import com.lweb.cache.entity.LQCacheKey;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class DefalutMainDBExecuter extends DBExecuter {

    protected Main getMain(Main main){
        if(main == null){
            return null;
        }
        if(main.getSectionId() != 0){
            Section section = LQCache.getIntance().getCache(LQCacheKey.Seciton,main.getSectionId());
            if(section == null){
                LogUtil.error(MainDBExecuter.class,"sectionId:"+main.getSectionId()+" cant find data");
            }else {
                int[] catesId = new int[section.getCateIds().size()];
                int i = 0;
                for(Integer cid:catesId){
                    catesId[i++]=cid;
                }
                main.setCateIds(catesId);
            }
        }
        return main;
    }

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        int mainType = (int) parater[0];
        int type = (int) parater[1];

        Main main = dataSource.ExecuteQueryOne(Main.class,"SELECT * FROM `system_main` WHERE `type`= ? AND mainType =? AND isDefault = TRUE",new Object[]{type,mainType});
        return getMain(main);
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
