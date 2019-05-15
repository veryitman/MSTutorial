/**
 * 用户注册.
 */
package com.veryitman.springboot.controller;

import com.veryitman.springboot.model.MSResponse;
import com.veryitman.springboot.model.MSResponseEnum;
import com.veryitman.springboot.model.MSUser;
import com.veryitman.springboot.util.MSUserUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "signup") // 注意这里不要在signup前后加"/"
public class MSSignupController {

    @PostMapping(value = "/name")
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
            user = MSUserUtil.createDefaultUser(userName, userPwd);
        }

        response.setResults(user);

        return response;
    }
}
