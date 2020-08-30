/**
 * 用户注册.
 */
package com.veryitman.springboot.controller;

import com.veryitman.springboot.model.MSResponse;
import com.veryitman.springboot.model.MSUserResponseEnum;
import com.veryitman.springboot.model.MSUser;
import com.veryitman.springboot.service.MSUserService;
import com.veryitman.springboot.util.MSUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "signup", tags = "用户模块-注册")
@RestController
@RequestMapping(value = "signup") // 注意这里不要在signup前后加"/"
public class MSSignupController {

    @Autowired
    private MSUserService userService;

    @CrossOrigin(origins = {"*"})
    @PostMapping(value = "/name")
    @ApiOperation(value = "用户名注册", httpMethod = "POST", notes = "用户名和密码注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "注册的用户名", required = true),
            @ApiImplicitParam(name = "userpwd", value = "注册的密码", required = true)
    })
    public MSResponse signup(@RequestParam(value = "username") String userName, @RequestParam(value = "userpwd") String userPwd) {
        MSResponse response = new MSResponse();
        MSUser user = null;
        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSUserResponseEnum signupError = MSUserResponseEnum.SignupInvalidInfo;
            response.setMsg(signupError.getMsg());
            response.setCode(signupError.getCode());
        } else {
            // 创建user表
            userService.createUserTable();
            // 检查用户数据库的‘user’表中是否有该用户？
            List<Map> query_users = userService.queryUserByUserName(userName);
            if (null == query_users || query_users.isEmpty()) {// 没有该用户的数据
                user = MSUserUtil.createUser(userName, userPwd);
                // 插入一条用户数据到数据表中
                userService.addUser(user);
                response.setCode(MSUserResponseEnum.SUCCESS.getCode());
                response.setMsg(MSUserResponseEnum.SUCCESS.getMsg());
            } else {// 用户数据库的‘user’表中有该用户信息
                // 返回错误信息：该用户已经注册过了
                MSUserResponseEnum signupError = MSUserResponseEnum.SignupHasExistUser;
                response.setMsg(signupError.getMsg());
                response.setCode(signupError.getCode());
            }
        }

        response.setResults(user);

        return response;
    }
}
