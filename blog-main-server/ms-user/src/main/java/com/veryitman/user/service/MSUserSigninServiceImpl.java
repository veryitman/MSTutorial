package com.veryitman.user.service;

import com.alibaba.fastjson.JSON;
import com.veryitman.core.model.MSResponse;
import com.veryitman.user.model.MSAuthToken;
import com.veryitman.user.model.MSUser;
import com.veryitman.user.model.MSUserResponseEnum;
import com.veryitman.user.util.MSAuthTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MSUserSigninServiceImpl implements MSUserSigninService {

    private MSUserDBService userDBService;

    private MSAuthTokenHelper tokenHelper;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setUserDBService(MSUserDBService userDBService) {
        this.userDBService = userDBService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setTokenHelper(MSAuthTokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @Override
    public MSResponse signinUsingUserName(String userName, String pwd) {
        MSUser user = null;
        MSResponse response = new MSResponse();
        if (null == userName || null == pwd || userName.length() <= 0 || pwd.length() <= 0) {
            MSUserResponseEnum responseEnum = MSUserResponseEnum.Login4SiginInvalidInfo;
            response.setCode(responseEnum.getCode());
            response.setMsg(responseEnum.getMsg());
        } else {
            String query_user_pwd = "";
            /** 查数据库的‘user’表中是否有该用户？*/
            List<Map> query_users = userDBService.queryUserByUserName(userName);
            if (query_users.isEmpty()) {// 没有该用户
                MSUserResponseEnum responseEnum = MSUserResponseEnum.LoginNoSuchUser;
                response.setCode(responseEnum.getCode());
                response.setMsg(responseEnum.getMsg());
            } else {// 有这个用户
                Map user_map = query_users.get(0);
                query_user_pwd = (String) user_map.get("accountPwd");
                if (!query_user_pwd.equals(pwd)) {
                    MSUserResponseEnum responseEnum = MSUserResponseEnum.LoginUserPwdError;
                    response.setCode(responseEnum.getCode());
                    response.setMsg(responseEnum.getMsg());
                } else {
                    // 将查询出来的map对象使用FastJson转换为MSUser对象
                    user = JSON.parseObject(JSON.toJSONString(user_map), MSUser.class);
                    // 生成token并存到Redis中
                    String userID = user.getUserID().toString();
                    String userToken = tokenHelper.generateToken(userID);
                    if (null != userToken || userToken.length() > 0) {
                        // 使用user_id作为key存储token
                        redisTemplate.opsForValue().set(userID, userToken);
                    }

                    MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
                    response.setCode(rspEnum.getCode());
                    response.setMsg(rspEnum.getMsg());
                }
            }
        }

        response.setResults(user);

        return response;
    }

    @Override
    public MSResponse fetchUserToken(String userID) {
        MSResponse response = new MSResponse();
        MSAuthToken authToken = new MSAuthToken();

        // TODO：判断该id是否符合user服务的userid生成规则

        // 数据库中没有找到该用户不允许获取token，即该用户不合法
        if (null != userID && userID.length() > 0) {
            List<Map> query_users = userDBService.queryUserByUid(Integer.parseInt(userID));
            if (null == query_users || query_users.size() <= 0) {
                MSUserResponseEnum rspEnum = MSUserResponseEnum.FetchTokenError;
                response.setCode(rspEnum.getCode());
                response.setMsg(rspEnum.getMsg());
                return response;
            }
        } else {
            MSUserResponseEnum rspEnum = MSUserResponseEnum.APIMissParamError;
            response.setCode(rspEnum.getCode());
            response.setMsg(rspEnum.getMsg());
            return response;
        }

        // 从Redis中获取
        String tokenAtRedis = (String) redisTemplate.opsForValue().get(userID);
        if (null != tokenAtRedis) {
            MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
            response.setCode(rspEnum.getCode());
            response.setMsg(rspEnum.getMsg());
            authToken.setAuthToken(tokenAtRedis);
        } else {
            String token = tokenHelper.generateToken(userID);
            if (null != token) {
                MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
                response.setCode(rspEnum.getCode());
                response.setMsg(rspEnum.getMsg());
                authToken.setAuthToken(token);
            } else {
                MSUserResponseEnum rspEnum = MSUserResponseEnum.FetchTokenError;
                response.setCode(rspEnum.getCode());
                response.setMsg(rspEnum.getMsg());
            }
        }
        response.setResults(authToken);
        return response;
    }

    @Override
    public MSResponse signinUsingToken(String userID, String token) {
        MSUser user = null;
        MSResponse response = new MSResponse();
        if (null == token || token.length() <= 0) {
            MSUserResponseEnum responseEnum = MSUserResponseEnum.Login4SiginInvalidInfo;
            response.setCode(responseEnum.getCode());
            response.setMsg(responseEnum.getMsg());
            return response;
        }

        // 从Redis中获取
        String tokenAtRedis = (String) redisTemplate.opsForValue().get(userID);
        if (null != tokenAtRedis && token.equalsIgnoreCase(tokenAtRedis)) {
            MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
            response.setCode(rspEnum.getCode());
            response.setMsg(rspEnum.getMsg());
            // 从数据库中查找该用户
            List<Map> query_users = userDBService.queryUserByUid(Integer.parseInt(userID));
            Map userMap = query_users.get(0);
            if (null != userMap) {
                user = JSON.parseObject(JSON.toJSONString(userMap), MSUser.class);
            }
        }

        response.setResults(user);

        return response;
    }

    @Override
    public MSResponse refreshUserToken(String token) {
        MSResponse response = new MSResponse();
        if (null == token || token.length() <= 0) {
            MSUserResponseEnum rspEnum = MSUserResponseEnum.APIMissParamError;
            response.setCode(rspEnum.getCode());
            response.setMsg(rspEnum.getMsg());
            return response;
        }

        String refreshToken = tokenHelper.refreshToken(token);

        if (null != refreshToken) {
            String userID = tokenHelper.userIDfromToken(token);
            // 使用user_id作为key存储token到Redis中
            redisTemplate.opsForValue().set(userID, refreshToken);
        }

        MSAuthToken authToken = new MSAuthToken();
        authToken.setAuthToken(refreshToken);
        MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
        response.setCode(rspEnum.getCode());
        response.setMsg(rspEnum.getMsg());
        response.setResults(authToken);

        return response;
    }
}
