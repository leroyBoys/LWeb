package com.lweb.action;

import com.lweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/21.
 */
@RestController
public class WebMain {
    @Autowired
    private AdminService adminService;
    @GetMapping("/main")
    public String main(HttpServletRequest request){
        request.setAttribute("menus",adminService.getSystemMenus());
        return "main";
    }



}
