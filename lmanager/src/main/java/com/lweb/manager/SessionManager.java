package com.lweb.manager;

import com.dbbase.moudle.admin.Admin;

import javax.servlet.http.HttpSession;

/**
 * Created by leroy:656515489@qq.com
 * 2018/7/9.
 */
public class SessionManager {
    private static SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public Admin getAdminFormSession(HttpSession session){
        return (Admin) session.getAttribute("user");
    }

    public void setAdminFormSession(HttpSession session,Admin admin){
       session.setAttribute("user",admin);
    }
}
