package com.veryitman.user.service;

import com.veryitman.core.model.MSResponse;
import com.veryitman.user.model.MSUser;
import com.veryitman.user.model.MSUserResponseEnum;
import com.veryitman.user.util.MSUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MSUserSignupServiceImpl implements MSUserSignupService {

    private MSUserDBService userDBService;

    @Autowired
    public void setUserDBService(MSUserDBService userDBService) {
        this.userDBService = userDBService;
    }

    @Override
    public MSResponse signupUsingUserName(String userName, String pwd) {
        MSUser user = null;
        MSResponse response = new MSResponse();
        if (null == userName || null == pwd || userName.length() <= 0 || pwd.length() <= 0) {
            MSUserResponseEnum signupError = MSUserResponseEnum.SignupInvalidInfo;
            response.setMsg(signupError.getMsg());
            response.setCode(signupError.getCode());
        } else {
            // 创建user表
            userDBService.createUserTable();
            // 检查用户数据库的‘user’表中是否有该用户？
            List<Map> query_users = userDBService.queryUserByUserName(userName);
            if (null == query_users || query_users.isEmpty()) {// 没有该用户的数据
                user = MSUserUtil.createUser(userName, pwd);
                // 插入一条用户数据到数据表中
                userDBService.addUser(user);
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
