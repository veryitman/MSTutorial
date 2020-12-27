/**
 * 用户登录.
 */
package com.veryitman.user.controller;

import com.veryitman.core.model.MSResponse;
import com.veryitman.user.service.MSUserSigninService;
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

@Api(value = "signin", tags = "用户模块-登录")
@Slf4j
@RestController
@RequestMapping(value = "signin") // 注意这里不要在signin前后加"/"
public class MSSigninController {

    private MSUserSigninService userSigninService;

    @Autowired
    public void setUserSigninService(MSUserSigninService userSigninService) {
        this.userSigninService = userSigninService;
    }

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

        MSResponse response = userSigninService.signinUsingUserName(userName, userPwd);

        return response;
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ApiOperation(value = "Token 登录", httpMethod = "GET", notes = "Token登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    public MSResponse siginWithToken(@RequestParam(value = "token") String token) {
        MSResponse response = userSigninService.signinUsingToken("", token);

        return response;
    }

//    @GetMapping(value = "/redisconn")
//    public String redis() {
//        log.info(redisTemplate.getValueSerializer().toString() + ", " + redisTemplate.getHashValueSerializer().toString());
//        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
//        log.info(connectionFactory.toString());
//        if (connectionFactory instanceof LettuceConnectionFactory) {
//            LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) connectionFactory;
//            log.info(lettuceConnectionFactory.getHostName() + ", " + lettuceConnectionFactory.getPort());
//        }
//        return connectionFactory.toString();
//    }
}
