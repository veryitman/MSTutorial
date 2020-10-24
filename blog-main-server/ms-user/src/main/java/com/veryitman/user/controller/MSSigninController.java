/**
 * 用户登录.
 */
package com.veryitman.user.controller;

import com.alibaba.fastjson.JSON;
import com.veryitman.core.model.MSResponse;
import com.veryitman.user.model.MSUser;
import com.veryitman.user.model.MSUserResponseEnum;
import com.veryitman.user.service.MSUserService;
import com.veryitman.user.util.MSHTTPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "signin", tags = "用户模块-登录")
@Slf4j
@RestController
@RequestMapping(value = "signin") // 注意这里不要在signin前后加"/"
public class MSSigninController {

    @Autowired
    private MSUserService userService;

    /**
     * User sigin with user's name and password.
     * <p>
     * http://localhost:8080/signin/name?username=myname&userpwd=123
     */
    @CrossOrigin(origins = {"*", "http://localhost:63344"})
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ApiOperation(value = "用户名登录", httpMethod = "GET", notes = "用户名登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "userpwd", value = "密码", required = true)
    })
    public MSResponse sigin(@RequestParam(value = "username") String userName, @RequestParam(value = "userpwd") String userPwd) {
        // 解析用户 IP
        {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
            String userIP = MSHTTPUtil.getIpAddr(httpServletRequest);
            log.info("MSBlog, sigin user's ip: " + ((null == userIP) ? "unknown" : userIP));
        }

        MSResponse response = new MSResponse();
        MSUser user = null;
        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSUserResponseEnum responseEnum = MSUserResponseEnum.Login4SiginInvalidInfo;
            response.setCode(responseEnum.getCode());
            response.setMsg(responseEnum.getMsg());
        } else {
            // 检查用户数据库的‘user’表中是否有该用户？
            List<Map> query_users = userService.queryUserByUserName(userName);
            if (query_users.isEmpty()) {// 没有该用户
                MSUserResponseEnum responseEnum = MSUserResponseEnum.LoginNoSuchUser;
                response.setCode(responseEnum.getCode());
                response.setMsg(responseEnum.getMsg());
            } else {// 有这个用户
                Map user_map = query_users.get(0);
                String query_user_name = (String) user_map.get("accountName");
                String query_user_pwd = (String) user_map.get("accountPwd");
                // 没有对应的用户名
                if (!query_user_name.equals(userName)) {
                    MSUserResponseEnum responseEnum = MSUserResponseEnum.LoginNoSuchUser;
                    response.setCode(responseEnum.getCode());
                    response.setMsg(responseEnum.getMsg());
                } else if (!query_user_pwd.equals(userPwd)) {
                    MSUserResponseEnum responseEnum = MSUserResponseEnum.LoginUserPwdError;
                    response.setCode(responseEnum.getCode());
                    response.setMsg(responseEnum.getMsg());
                } else {// 查询到了该用户
                    // 将查询出来的map对象使用FastJson转换为MSUser对象
                    user = JSON.parseObject(JSON.toJSONString(user_map), MSUser.class);
                    MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
                    response.setCode(rspEnum.getCode());
                    response.setMsg(rspEnum.getMsg());
                }
            }
        }

        response.setResults(user);

        return response;
    }
}
