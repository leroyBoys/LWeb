package com.lweb.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@RestController
@RequestMapping("/login")
public class LoginAction {

    @RequestMapping
    public String toLogin(){
        return "loginloginloginloginloginlogin";
    }
}
