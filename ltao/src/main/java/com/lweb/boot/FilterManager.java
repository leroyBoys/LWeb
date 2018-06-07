package com.lweb.boot;

import com.lweb.manager.ErrorCode;
import com.lweb.manager.RouteManager;
import com.lweb.manager.TimeCacheManager;
import com.lweb.manager.URLManager;
import com.lweb.moudle.SessionContainer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
@WebFilter(urlPatterns="/*")
@Component
public class FilterManager  implements Filter {

    private static final URLManager urlManager = URLManager.instance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
      /*  PrintTool.outTime(9000000, () -> {
           // resourceUrlProvider.getForLookupPath(uri);
            updatae(request,servletResponse,filterChain);
        });
*/

        System.out.println("uri:"+uri);
        if(uri == null || uri.isEmpty()){
            checkResponseCount(request,response,filterChain);
            return;
        }

        //白名单验证
        final int length = uri.length();
        if(length == 1 && uri.charAt(0) == '/'){
            checkResponseCount(request,response,filterChain);
            return;
        }else if(length < 3){
            checkResponseCount(request,response,filterChain);
            //RouteManager.getInstance().outErrorCode((HttpServletResponse) servletResponse, ErrorCode.URLLow);
            return;
        }else if(urlManager.isStatic(uri,length)){
            next(request,servletResponse,filterChain);
            return;
        }else if(urlManager.isWhite(uri,length)){
            checkResponseCount(request,response,filterChain);
            return;
        }

        checkLogin(request,response,filterChain);
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        SessionContainer sessionContainer = checkSessionContainer(request);

        //登录验证
        if(sessionContainer.getUserInfo() != null){
            checkResponseCount(request,response,sessionContainer,filterChain);
        }else {
            request.setAttribute("msg", "请先登录");
            RouteManager.getInstance().sendRedirect(response,urlManager.LOGIN_URL());
        }

    }

    private SessionContainer checkSessionContainer(HttpServletRequest request){

        HttpSession session = request.getSession();
        System.out.println("checkSessionContainer:uri:"+request.getRequestURI()+"   "+session.getId());

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
        return sessionContainer;
    }

    private void checkResponseCount(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        System.out.println("=========checkResponseCount:uri:"+request.getRequestURI());

        this.checkResponseCount(request,response,checkSessionContainer(request),filterChain);
    }

    private void checkResponseCount(HttpServletRequest request, HttpServletResponse response,SessionContainer sessionContainer, FilterChain filterChain) {

        System.out.println("checkResponseCount:uri:"+request.getRequestURI()+"   "+sessionContainer.getRequestCount());

        ///请求频率验证
        if(sessionContainer.addRequest() > SessionContainer.maxRequestingCount){
            return;
        }

        TimeCacheManager.instance().refresh();
        next(request,response,filterChain);
        //请求频率还原
        sessionContainer.removeRequest();
    }

    private void updatae(HttpServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        String uri = servletRequest.getRequestURI();
        if(uri == null || uri.isEmpty()){
            return;
        }

        final int length = uri.length();
        if(length == 1 && uri.charAt(0) == '/'){
            return;
        }
        if(urlManager.isWhite(uri,length)){
            return;
        }
    }

    private void next(HttpServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
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
