/**
 * 用户登录.
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
@RequestMapping(value = "/signin")
public class MSSigninController {

    /**
     * User sigin with user's name and password.
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public MSResponse sigin(@RequestParam(value = "username") String userName, @RequestParam(value = "pwd") String userPwd) {
        MSResponse response = new MSResponse();
        MSUser user = null;

        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSResponseEnum responseEnum = MSResponseEnum.Login4SiginInvalidInfo;
            response.setCode(responseEnum.getCode());
            response.setMsg(responseEnum.getMsg());
        } else {
            user = new MSUser();
            user.setName("veryitman");
            user.setGender(1);
            user.setAge(20);
            user.setUserID("1");
            MSResponseEnum rspEnum = MSResponseEnum.SUCCESS;
            response.setCode(rspEnum.getCode());
            response.setMsg(rspEnum.getMsg());
        }

        response.setResults(user);

        return response;
    }
}
