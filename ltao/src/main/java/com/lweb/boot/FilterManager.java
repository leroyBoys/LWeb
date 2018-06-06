package com.lweb.boot;

import com.lweb.manager.ErrorCode;
import com.lweb.manager.RouteManager;
import com.lweb.manager.URLManager;
import com.lweb.moudle.SessionContainer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
public class FilterManager implements Filter {

    private static final URLManager urlManager = URLManager.instance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();
        if(uri == null || uri.isEmpty()){
            next(servletRequest,servletResponse,filterChain);
            return;
        }

        final int length = uri.length();
        if(length == 1 && uri.charAt(0) == '/'){
            next(servletRequest,servletResponse,filterChain);
            return;
        }else if(length < 3){
            RouteManager.getInstance().outErrorCode((HttpServletResponse) servletResponse, ErrorCode.URLLow);
            return;
        }

        if(urlManager.isWhite(uri,length)){
            next(servletRequest,servletResponse,filterChain);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SessionContainer sessionContainer = (SessionContainer) session.getAttribute(SessionContainer.key);
        if(sessionContainer == null){

            synchronized (session){
                sessionContainer = (SessionContainer) session.getAttribute(SessionContainer.key);
                if(sessionContainer == null){
                    sessionContainer = new SessionContainer();
                    session.setAttribute(SessionContainer.key,sessionContainer);
                }
            }
        }

        if(sessionContainer.addRequest() > SessionContainer.maxRequestingCount){
            return;
        }

        if(sessionContainer.getUserInfo() != null){
            next(servletRequest,servletResponse,filterChain);
        }else {
            request.setAttribute("msg", "请先登录");
            RouteManager.getInstance().sendRedirect(response,urlManager.LOGIN_URL());
        }
        sessionContainer.removeRequest();
    }

    /**
     * 登录检测
     * @param uri
     * @param request
     * @param response
     * @param filterChain
     */
    private void checkLogin(String uri,HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {


    }

 /*   private void updatae(HttpServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        String uri = servletRequest.getRequestURI();
        if(uri == null || uri.isEmpty()){
            return;
        }

        final int length = uri.length();
        if(length == 1 && uri.charAt(0) == '/'){
            return;
        }
        if(urlManager.isWhite2(uri,length)){
            return;
        }
    }
*/

    private void next(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
