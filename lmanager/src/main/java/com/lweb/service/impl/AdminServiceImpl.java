package com.lweb.service.impl;

import com.dbbase.moudle.admin.Admin;
import com.lgame.util.encry.MD5Tool;
import com.lqsmart.core.LQStart;
import com.lqsmart.mysql.entity.LQPage;
import com.lweb.entity.QueryPage;
import com.lweb.entity.SystemMenu;
import com.lweb.manager.CacheManager;
import com.lweb.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public Admin login(Admin admin) {
        admin = LQStart.getJdbcManager().getMaster().ExecuteQueryOne(Admin.class,"select * from system_admin where password =? and name=?",new Object[]{MD5Tool.GetMD5Code(admin.getPassword()),admin.getName()});
        return admin;
    }

    @Override
    public List<SystemMenu> getSystemMenus() {
        List<SystemMenu> menus = CacheManager.getInstance().getSystemMenus();
        if(menus != null && !menus.isEmpty()){
            return menus;
        }

        menus = LQStart.getJdbcManager().getMaster().ExecuteQueryList(SystemMenu.class);
        if(menus == null){
            return null;
        }
        CacheManager.getInstance().initMenus(menus);
        return CacheManager.getInstance().getSystemMenus();
    }

    @Override
    public int editorMenus(SystemMenu systemMenu) {
        LQStart.getJdbcManager().getMaster().ExecuteEntity(systemMenu);
        CacheManager.getInstance().getSystemMenus().clear();
        return systemMenu.getId();
    }

    @Override
    public Object delMenu(int id) {
        LQStart.getJdbcManager().getMaster().DelEntity(SystemMenu.class,id);
        CacheManager.getInstance().getSystemMenus().clear();
        return true;
    }

    @Override
    public QueryPage getAdmins(QueryPage lqPage) {
        return LQStart.getJdbcManager().getMaster().ExecuteQueryForPage(Admin.class,lqPage);
    }

    @Override
    public Admin getAdminById(int id) {
        return LQStart.getJdbcManager().getMaster().ExecuteQueryById(Admin.class,id);
    }

    @Override
    public void saveAdmin(Admin admin) {
        LQStart.getJdbcManager().getMaster().ExecuteEntity(admin);
    }

    @Override
    public void delAdmin(int id) {
        LQStart.getJdbcManager().getMaster().DelEntity(Admin.class,id);
    }
}
