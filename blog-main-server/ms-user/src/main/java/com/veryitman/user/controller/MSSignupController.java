/**
 * 用户注册.
 */
package com.veryitman.user.controller;

import com.veryitman.core.model.MSResponse;
import com.veryitman.user.service.MSUserSignupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "signup", tags = "用户模块-注册")
@RestController
@RequestMapping(value = "signup") // 注意这里不要在signup前后加"/"
public class MSSignupController {

    private MSUserSignupService userSignupService;

    @Autowired
    public void setUserSignupService(MSUserSignupService userSignupService) {
        this.userSignupService = userSignupService;
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping(value = "/name")
    @ApiOperation(value = "用户名注册", httpMethod = "POST", notes = "用户名和密码注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "注册的用户名", required = true),
            @ApiImplicitParam(name = "userpwd", value = "注册的密码", required = true)
    })
    public MSResponse signup(@RequestParam(value = "username") String userName, @RequestParam(value = "userpwd") String userPwd) {
        MSResponse response = userSignupService.signupUsingUserName(userName, userPwd);
        return response;
    }
}
