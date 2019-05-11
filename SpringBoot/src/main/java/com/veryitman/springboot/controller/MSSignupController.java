/**
 * 用户注册.
 */
package com.veryitman.springboot.controller;

import com.veryitman.springboot.model.MSResponse;
import com.veryitman.springboot.model.MSResponseEnum;
import com.veryitman.springboot.model.MSUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signup")
public class MSSignupController {

    @RequestMapping(name = "/name", method = RequestMethod.GET)
    public MSResponse signup(@RequestParam(value = "username") String userName, @RequestParam(value = "pwd") String userPwd) {
        MSResponse response = new MSResponse();
        MSUser user = null;
        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSResponseEnum signupError = MSResponseEnum.SignupInvalidInfo;
            response.setMsg(signupError.getMsg());
            response.setCode(signupError.getCode());
        } else {
            response.setCode(MSResponseEnum.SUCCESS.getCode());
            response.setMsg(MSResponseEnum.SUCCESS.getMsg());
            user = new MSUser();
            user.setUserID("123");
            user.setAge(20);
            user.setGender(1);
            user.setName(userName);
        }

        response.setResults(user);

        return response;
    }

    /**
     * User sigin with user's name and password.
     */
    @RequestMapping(value = "/name1", method = RequestMethod.GET)
    public MSResponse _sigin22(@RequestParam(value = "username") String userName, @RequestParam(value = "pwd") String userPwd) {
        MSResponse response1 = new MSResponse();
        MSUser user = null;

        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSResponseEnum responseEnum = MSResponseEnum.Login4SiginInvalidInfo;
            response1.setCode(responseEnum.getCode());
            response1.setMsg(responseEnum.getMsg());
        } else {
            user = new MSUser();
            user.setName("veryitman");
            user.setGender(1);
            user.setAge(20);
            user.setUserID("1");
            MSResponseEnum rspEnum = MSResponseEnum.SUCCESS;
            response1.setCode(rspEnum.getCode());
            response1.setMsg(rspEnum.getMsg());
        }

        response1.setResults(user);

        return response1;
    }
}
