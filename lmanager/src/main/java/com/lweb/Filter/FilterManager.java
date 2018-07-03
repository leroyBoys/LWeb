package com.lweb.Filter;

import com.lweb.manager.RouteManager;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@WebFilter(urlPatterns="/*")
@Component
public class FilterManager implements Filter {
    private String LOGIN_URL = "/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();


        //白名单验证
        final int length = uri.length();
        if(uri == null || uri.isEmpty() || length == 1 && uri.charAt(0) == '/'){
            RouteManager.getInstance().sendRedirect(response,LOGIN_URL);
            return;
        }else if(uri.endsWith(".js")  || uri.endsWith(".css")  || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.startsWith("/img/")|| uri.startsWith("/font")){
            next(request,response,filterChain);
            return;
        }

        System.out.println(uri);
        if(uri.startsWith(LOGIN_URL)){
            next(request,response,filterChain);
            return;
        }

        checkLogin(request,response,filterChain);
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        Object user = request.getSession().getAttribute("user");

        user = new Object();
        //登录验证
        if(user != null){
            next(request,response,filterChain);
        }else {
            request.setAttribute("msg", "请先登录");
            RouteManager.getInstance().sendRedirect(response,LOGIN_URL);
        }

    }


    private void next(HttpServletRequest servletRequest, HttpServletResponse response, FilterChain filterChain){
        try {
            filterChain.doFilter(servletRequest,response);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    @Override
    public void destroy() {

    }
}
