package com.lweb.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@RestController
@RequestMapping("/")
public class IndexAction {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/phone")
    String pcHome() {
        return "phone vHello World!";
    }
}
