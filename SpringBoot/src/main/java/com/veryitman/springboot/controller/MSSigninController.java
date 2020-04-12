/**
 * 用户登录.
 */
package com.veryitman.springboot.controller;

import com.veryitman.springboot.model.MSResponse;
import com.veryitman.springboot.model.MSResponseEnum;
import com.veryitman.springboot.model.MSUser;
import com.veryitman.springboot.service.MSUserService;
import com.veryitman.springboot.util.MSUserUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value="signin", tags="用户模块-登录")
@RestController
@RequestMapping(value = "signin") // 注意这里不要在signin前后加"/"
public class MSSigninController {

    @Autowired
    private MSUserService userService;

    /**
     * User sigin with user's name and password.
     *
     * http://localhost:8080/signin/name?username=mark&userpwd=123
     */
    @CrossOrigin(origins = {"*", "http://localhost:8082"})
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ApiOperation(value = "用户名登录", httpMethod = "GET", notes = "用户名登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "userpwd", value = "密码", required = true)
    })
    public MSResponse sigin(@RequestParam(value = "username") String userName, @RequestParam(value = "userpwd") String userPwd) {
        MSResponse response = new MSResponse();
        MSUser user = null;
        if (null == userName || null == userPwd || userName.length() <= 0 || userPwd.length() <= 0) {
            MSResponseEnum responseEnum = MSResponseEnum.Login4SiginInvalidInfo;
            response.setCode(responseEnum.getCode());
            response.setMsg(responseEnum.getMsg());
        } else {
            // 检查用户数据库的‘user’表中是否有该用户？
            List<Map> query_users = userService.queryUserByUserName(userName);
            if (query_users.isEmpty()) {// 没有该用户
                MSResponseEnum responseEnum = MSResponseEnum.LoginNoSuchUser;
                response.setCode(responseEnum.getCode());
                response.setMsg(responseEnum.getMsg());
            } else {// 有这个用户
                Map user_map = query_users.get(0);
                String query_user_name = (String) user_map.get("user_name");
                // 没有对应的用户名
                if (!query_user_name.equals(userName)) {
                    MSResponseEnum responseEnum = MSResponseEnum.LoginNoSuchUser;
                    response.setCode(responseEnum.getCode());
                    response.setMsg(responseEnum.getMsg());
                } else {// 查询到了该用户
                    user = MSUserUtil.createUser(userName, userPwd);
                    MSResponseEnum rspEnum = MSResponseEnum.SUCCESS;
                    response.setCode(rspEnum.getCode());
                    response.setMsg(rspEnum.getMsg());
                }
            }
        }

        response.setResults(user);

        return response;
    }
}
