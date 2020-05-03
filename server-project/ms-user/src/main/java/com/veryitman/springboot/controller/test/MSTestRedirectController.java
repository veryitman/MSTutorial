package com.veryitman.springboot.controller.test;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "testredirect")
public class MSTestRedirectController {

    @GetMapping(value = "/access/web")
    public String redirect() {
        return "redirect:/testredirect/index/realweb?parameter=coming";
    }

    @GetMapping(value = "/access/web2")
    public String redirect2(HttpServletResponse response) {
        try {
            // 方法1：自定义状态码方式
            //response.setHeader("Location", "http://localhost:8080/testredirect/index/realweb?parameter=coming");
            //response.sendError(301);
            // 方法2：sendRedirect，默认返回的状态码是 302
            response.sendRedirect("/testredirect/index/realweb?parameter=coming");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return "";
        }
    }

    @ResponseBody
    @GetMapping(value = "/index/realweb")
    public String real(HttpServletRequest request) {
        return "redirect happened：" + JSON.toJSONString(request.getParameterMap());
    }
}
