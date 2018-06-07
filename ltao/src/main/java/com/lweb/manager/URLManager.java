package com.lweb.manager;

import com.lweb.moudle.AppConfig;

import java.util.*;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public class URLManager {
    private String LOGIN_URL ="/login";
    private static URLMatchData[] urlMatchDatas;
    private final static URLManager instance = new URLManager();
    private URLManager(){}
    public static final URLManager instance(){
        return instance;
    }

    protected void init(AppConfig appConfig){
        LOGIN_URL = appConfig.getLoginURI();
        String[] urls = appConfig.getWhiteURI().split(",");

        Set<String> urlSets = new HashSet<>();
        urlSets.add(LOGIN_URL);
        Collections.addAll(urlSets,urls);
        Map<String,Set<String>> map = urlMap(urlSets);

        urlMatchDatas = new URLMatchData[map.size()];
        int i = 0;
        for(Map.Entry<String,Set<String>> entry:map.entrySet()){
            urlMatchDatas[i++] = new URLMatchData(entry.getKey(),entry.getValue());
        }
    }

    private Map<String,Set<String>> urlMap(Set<String> urlSets){
        Map<String,Set<String>> map = new HashMap<>();

        Set<String> tmpSet = new HashSet<>();
        for(String url:urlSets){
            url = url.trim();
            if(url.isEmpty()){
                continue;
            }

            if(!url.startsWith("/")){
                url="/"+url;
            }else if(url.equals("/")){
                tmpSet = new HashSet<>();
                tmpSet.add(url);
                map.put(url,tmpSet);
                break;
            }
            String key = "/"+url.split("/")[1];
            tmpSet = map.get(key);
            if(tmpSet == null){
                tmpSet = new HashSet<>();
                map.put(key,tmpSet);
            }
            tmpSet.add(url);
        }
        return map;
    }

    public String LOGIN_URL() {
        return LOGIN_URL;
    }

   public boolean isWhite(String uri, final int length){
        final char c = uri.charAt(URLMatchData.idex);
        for(int i =0,size=urlMatchDatas.length ;i<size;i++){
            if(urlMatchDatas[i].match(uri,c,length)){
                return true;
            }
        }
        return false;
    }

    public boolean isStatic(String uri, final int length){
        char c;
        for(int i =length-2 ;i>=0;i--){
            c = uri.charAt(i);
            if('.' == c){
                return true;
            }else if('/' == c){
                return false;
            }
        }
        return false;
    }

}
