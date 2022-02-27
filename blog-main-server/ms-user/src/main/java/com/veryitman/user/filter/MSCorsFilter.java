/**
 * 自定义 Filter 解决跨域问题。
 */
package com.veryitman.user.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@Component
//@WebFilter(urlPatterns = { "/signin/name" })
public class MSCorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if ("OPTIONS".equals(request.getMethod())) {
            System.out.println("HTTP's OPTIONS Coming");
        }

        HttpServletResponse response = (HttpServletResponse) res;
        // 设置指定的域名
        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:8082");

        // 设置所有的请求域名都可以
        //response.setHeader("Access-Control-Allow-Origin", "*");

        // 设置多个域名支持
        String[] allowDomain = {"http://localhost:8082", "http://localhost:8083", "http://localhost:8085", "http://localhost:8087"};
        Set allowedOrigins = new HashSet(Arrays.asList(allowDomain));
        String originHeader = ((HttpServletRequest) req).getHeader("Origin");
        System.out.println("originHeader: " + originHeader);
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }

        // 设置允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        // 设置缓存时间，单位秒，在改时间内不需要再发送预检验请求，可以缓存该结果
        response.setHeader("Access-Control-Max-Age", "10");
        // 设置允许跨域请求包含content-type头
        response.setHeader("Access-Control-Allow-Headers", "*");

        System.out.println("Filter has been used.");

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
