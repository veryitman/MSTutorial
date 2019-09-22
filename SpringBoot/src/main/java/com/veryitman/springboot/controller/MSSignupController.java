/**
 * 用户注册.
 */
package com.veryitman.springboot.controller;

import com.veryitman.springboot.model.MSResponse;
import com.veryitman.springboot.model.MSResponseEnum;
import com.veryitman.springboot.model.MSUser;
import com.veryitman.springboot.util.MSUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value="signup", tags="用户模块")
@RestController
@RequestMapping(value = "signup") // 注意这里不要在signup前后加"/"
public class MSSignupController {

    @CrossOrigin(origins = {"*"})
    @PostMapping(value = "/name")
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户名和密码注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "注册的用户名", required = true),
            @ApiImplicitParam(name = "userpwd", value = "注册的密码", required = true)
    })
    public MSResponse signup(@RequestParam(value = "username") String userName, @RequestParam(value = "userpwd") String userPwd) {
        MSResponse response = new MSResponse();
        MSUser user = null;
        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSResponseEnum signupError = MSResponseEnum.SignupInvalidInfo;
            response.setMsg(signupError.getMsg());
            response.setCode(signupError.getCode());
        } else {
            response.setCode(MSResponseEnum.SUCCESS.getCode());
            response.setMsg(MSResponseEnum.SUCCESS.getMsg());
            user = MSUserUtil.createUser(userName, userPwd);
        }

        response.setResults(user);

        return response;
    }
}
