package com.veryitman.user.service;

import com.alibaba.fastjson.JSON;
import com.veryitman.core.model.MSResponse;
import com.veryitman.user.model.MSUser;
import com.veryitman.user.model.MSUserResponseEnum;
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

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setUserDBService(MSUserDBService userDBService) {
        this.userDBService = userDBService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
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
            /** 从Redis里面查找该用户 */
            MSUser redisUser = (MSUser) redisTemplate.opsForValue().get(userName);
            String query_user_pwd = "";
            if (null != redisUser && pwd.equals(redisUser.getAccountName())) { // Redis 里面有该用户信息
                log.info("Redis 中找到了 " + userName);
                MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
                user = redisUser;
                response.setCode(rspEnum.getCode());
                response.setMsg(rspEnum.getMsg());
            } else { // Redis 里面没有该用户信息
                log.info("Redis 中没有找到 " + userName);
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
                        // 缓存到 Redis，使用userName作为key
                        String userNameKey = String.valueOf(user.getAccountName());
                        redisTemplate.opsForValue().set(userNameKey, user);
                        log.info("MySQL 中找到了 " + userName + ", 并存到 Redis 中");

                        MSUserResponseEnum rspEnum = MSUserResponseEnum.SUCCESS;
                        response.setCode(rspEnum.getCode());
                        response.setMsg(rspEnum.getMsg());
                    }
                }
            }
        }

        response.setResults(user);

        return response;
    }
}
