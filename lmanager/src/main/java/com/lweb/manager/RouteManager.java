package com.lweb.manager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public class RouteManager {
    private static RouteManager ourInstance = new RouteManager();

    public static RouteManager getInstance() {
        return ourInstance;
    }
    private RouteManager() {
    }

    public void outErrorJson(HttpServletResponse response, String error){
        response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
        try {
            OutputStream stream = response.getOutputStream();
            stream.write(error.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRedirect(HttpServletResponse response,String url){
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
