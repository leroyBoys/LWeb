package com.lweb.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@Controller
public class ViewAction {
    @RequestMapping("/view/{v}")
    public String view(@PathVariable String v) {
        return v.replace('_','/');
    }

    @RequestMapping("/userview/{v}")
    public String userView(@PathVariable String v) {
        return v.replace('_','/');
    }
}
