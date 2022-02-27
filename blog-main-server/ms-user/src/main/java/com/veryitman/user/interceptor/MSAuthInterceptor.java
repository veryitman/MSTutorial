/**
 * @Description：自定义拦截器，每次请求到达之前都会验证token
 * @author：Mark
 * @CreateDate：2020.12.29
 * @update：[1][2020.12.29][Mark][New]
 * @Note：配合MSAuthConfigurer使用
 */

package com.veryitman.user.interceptor;

import com.veryitman.user.util.MSAuthTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MSAuthInterceptor implements HandlerInterceptor {
    private static final String REQUEST_TOKEN_KEY = "token";

    private MSAuthTokenHelper tokenHelper;

    @Autowired
    public void setTokenHelper(MSAuthTokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 请求的Header中拿
        String token = request.getHeader(REQUEST_TOKEN_KEY);
        // Header中拿不到token
        if (null == token) {
            String[] tokens = request.getParameterValues("token");
            if (null != tokens && tokens.length > 0) {
                token = tokens[0];
            }
        }

        if (tokenHelper.verifyToken(token)) {
            return true;
        }

        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            Map<String, Object> result = new HashMap<>(2);
            result.put("code", 400);
            result.put("msg", "用户令牌token无效");
            result.put("data", null);
            writer.print(result);
        } catch (IOException e) {

        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return false;
    }
}
