package com.lweb.action;

import com.dbbase.enums.PlatformType;
import com.lweb.service.system.IndexService;
import com.lweb.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@RestController
@RequestMapping("/")
public class IndexAction {
    @Autowired
    private SystemService systemService;
    @Autowired
    private IndexService indexService;

    private int getDefaultPage(Integer page){
        return page == null?1:page;
    }
    @RequestMapping
    Object home(Integer type,Integer page) {
        type = type==null?PlatformType.weixin.getDBValue():type;
        return indexService.getDefaultMain(type,getDefaultPage(page));
    }

    @RequestMapping("main")
    Object getMain(Integer type,Integer page) {
        type = type==null?PlatformType.weixin.getDBValue():type;
        return indexService.getDefaultMain(type,getDefaultPage(page));
    }

    @RequestMapping("search")
    Object getSearch(Integer type,Integer page) {
        type = type==null?PlatformType.weixin.getDBValue():type;
        return indexService.getDefaultMain(type,getDefaultPage(page));
    }

    @RequestMapping("article")
    Object getArticle(Integer type,Integer page) {
        type = type==null?PlatformType.weixin.getDBValue():type;
        return indexService.getDefaultMain(type,getDefaultPage(page));
    }
}
