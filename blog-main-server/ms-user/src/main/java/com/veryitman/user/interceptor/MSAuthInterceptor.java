package com.veryitman.user.interceptor;

import com.veryitman.user.util.MSTokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MSAuthInterceptor implements HandlerInterceptor {
    private static final String REQUEST_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler);

        String requestMethod = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader(REQUEST_TOKEN_KEY);

        if (MSTokenUtil.verifyToken(token)) {
            return true;
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        // response 返回告知调用者

        return false;
    }
}
