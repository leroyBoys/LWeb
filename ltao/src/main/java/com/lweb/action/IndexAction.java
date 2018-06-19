package com.lweb.action;

import com.dbbase.enums.MainType;
import com.dbbase.enums.PlatformType;
import com.dbbase.log.LogUtil;
import com.dbbase.moudle.system.Main;
import com.lweb.cache.LQCache;
import com.lweb.cache.entity.LQCacheKey;
import com.lweb.moudle.ErrorCode;
import com.lweb.moudle.ErrorResponse;
import com.lweb.service.maintype.MainTypeServiceExucter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/7.
 */
@RestController
@RequestMapping("/")
public class IndexAction {

    private Map<String,Object> turnResponseForMainData(Main main, int curPage,List results){
        Map<String,Object> map = new HashMap<>();
        if(main != null){
            map.put("main",main);
        }
        map.put("page",curPage);
        if(results != null){
            map.put("results",results);
        }
        return map;
    }

    @RequestMapping
    Object home(Integer type,Integer page,Integer id) {
        return getResponseForMainData(type==null?PlatformType.weixin.getDBValue():type,page,id,MainTypeServiceExucter.Main);
    }

    private Main getMain(int platformType,MainType mainType,Integer id){
        LQCache lqCache = LQCache.getIntance();
        if(id == null){
            Main main = lqCache.getCache(LQCacheKey.DefaultMain, mainType.getDBValue(),platformType);
            if(main == null){
                LogUtil.error(IndexAction.class,"mainType:"+mainType+",platformType:"+platformType+" not config");
                return null;
            }
            return main;
        }

        return LQCache.getIntance().getCache(LQCacheKey.Main, id);
    }

    private Object getResponseForMainData(Integer type,Integer page,Integer id, MainTypeServiceExucter mainTypeServiceExucter){
        Main main = null;
        List results = null;
        if(page == null || page <= 1){
            page = 1;
            main = getMain(type,mainTypeServiceExucter.getMainType(),id);
            if(main == null){
                return ErrorResponse.value(ErrorCode.ParamterError);
            }
            results = mainTypeServiceExucter.getMainTypeService().queryResults(main.getId(),page);
        }else if(id == null){
            LogUtil.error(this.getClass(),"id is empity");
            return ErrorResponse.value(ErrorCode.ParamterError);
        }else {
            results = mainTypeServiceExucter.getMainTypeService().queryResults(id,page);
        }
        return turnResponseForMainData(main,page,results);
    }

    @RequestMapping("search")
    Object getSearch(Integer type,Integer page,Integer id) {
        return getResponseForMainData(type==null?PlatformType.weixin.getDBValue():type,page,id,MainTypeServiceExucter.Search);
    }

    @RequestMapping("article")
    Object getArticle(Integer type,Integer page,Integer id) {
        return getResponseForMainData(type==null?PlatformType.weixin.getDBValue():type,page,id,MainTypeServiceExucter.Article);
    }
}
