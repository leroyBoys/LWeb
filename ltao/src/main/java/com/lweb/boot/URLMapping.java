package com.lweb.boot;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *  以后优化mapping，尽量只使用一个
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public class URLMapping extends RequestMappingHandlerMapping {

    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) throws Exception {
        return super.getHandlerInternal(request);
    }

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        return super.lookupHandlerMethod(lookupPath, request);
    }
}
