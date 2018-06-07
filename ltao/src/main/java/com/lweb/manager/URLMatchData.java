package com.lweb.manager;

import java.util.Set;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public class URLMatchData {
    private final char firstChar;
    private final String[] urlArray;
    private final int minLength;
    public static final int idex = 1;//首先检测第几位

    public URLMatchData(String key,Set<String> urlSets){
        this.firstChar = key.charAt(idex);
        this.urlArray = new String[urlSets.size()];
        urlSets.toArray(this.urlArray);

        int min = 99;
        for(String url:urlSets){
            min = Math.min(url.length(),min);
        }
        minLength = min;
    }

    public boolean match(String url,char c,int length){
        if(c != firstChar || length < minLength){
            return false;
        }

        for(int i = 0,arryLength = urlArray.length;i<arryLength;i++){
            if(url.startsWith(urlArray[i])){
                return true;
            }
        }
        return false;
    }

}
