package com.lweb.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@Controller
public class UserAction {
    @RequestMapping("/")
    String hhha() {
        return "Hello World!";
    }
}
