package com.lweb.action;

import com.dbbase.moudle.admin.Admin;
import com.lgame.util.json.FastJsonTool;
import com.lweb.entity.SystemMenu;
import com.lweb.manager.CacheManager;
import com.lweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
@Controller
public class LoginAction {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logined")
    public String logined(HttpServletRequest request, Admin admin){
        admin = adminService.login(admin);
        if(admin == null){
            request.setAttribute("error","用户名或者密码错误!");
            return "/login";
        }

        request.getSession().setAttribute("user",admin);
        return "redirect:main";
    }

}
