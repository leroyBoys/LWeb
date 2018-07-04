package com.lweb.entity;

import com.lqsmart.mysql.entity.LQPage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2018/7/4.
 */
public class QueryPage extends LQPage {

    public void initConditions(HttpServletRequest request) {
        Map<String, String[]> stringMap = request.getParameterMap();
        if(stringMap == null||stringMap.isEmpty()){
            return;
        }

        for(Map.Entry<String,String[]> entry:stringMap.entrySet()){
            if(entry.getKey().indexOf(0) != '_'){
                continue;
            }

            if(entry.getKey().startsWith("_like_")){
                getLikeConditions().put(entry.getKey().substring(6),entry.getValue()[0]);
            }else {
                getConditions().put(entry.getKey().substring(1),entry.getValue()[0]);
            }
        }
    }
}
