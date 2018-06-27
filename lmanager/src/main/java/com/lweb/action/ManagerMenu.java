package com.lweb.action;

import com.dbbase.moudle.admin.Admin;
import com.dbbase.moudle.system.Menu;
import com.lgame.util.json.FastJsonTool;
import com.lweb.entity.SystemMenu;
import com.lweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/21.
 */
@Controller
@RequestMapping
public class ManagerMenu {
    @Autowired
    private AdminService adminService;
    @GetMapping("/main")
    public String main(HttpServletRequest request){
        request.setAttribute("menus",adminService.getSystemMenus());
        return "main";
    }

    @GetMapping("/manager/menus")
    @ResponseBody
    public Object menus(HttpServletRequest request){
        return adminService.getSystemMenus();
    }

    @PostMapping("/manager/menu/editor")
    @ResponseBody
    public Object editorMenus(SystemMenu systemMenu){
		adminService.editorMenus(systemMenu);
        return systemMenu;
    }

    @GetMapping("/manager/menu/del")
    @ResponseBody
    public Object delMenu(int id){
        return adminService.delMenu(id);
    }

}
