package com.lweb.service;

import com.dbbase.moudle.admin.Admin;
import com.lqsmart.mysql.entity.LQPage;
import com.lweb.entity.QueryPage;
import com.lweb.entity.SystemMenu;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
public interface AdminService {
    public Admin login(Admin admin);
    public List<SystemMenu> getSystemMenus();

    int editorMenus(SystemMenu systemMenu);

    Object delMenu(int id);

    QueryPage getAdmins(QueryPage lqPage);
    Admin getAdminById(int id);
    void  saveAdmin(Admin admin);
}
