/**
 * @Description：登录服务
 * @author：Mark
 * @CreateDate：2020.12.25
 * @update：[1][2020.12.25][Mark][New]
 */
package com.veryitman.user.service;

import com.veryitman.core.model.MSResponse;

public interface MSUserSigninService {

    /**
     * 用户名/密码登录
     *
     * @param userName 用户名
     * @param pwd 用户设置的密码
     * @return @see MSResponse
     */
    MSResponse signinUsingUserName(String userName, String pwd);

    /**
     * 使用token登录
     * @param userID 用户id
     * @param token token
     * @return @see MSResponse
     */
    MSResponse signinUsingToken(String userID, String token);

    /***
     * 获取 token
     * @param userID 当前登录的userID
     * @return @see MSResponse
     */
    MSResponse fetchUserToken(String userID);

    /***
     * 刷新 token
     * @param token 当前登录用户的token
     * @return @see MSResponse
     */
    MSResponse refreshUserToken(String token);
}
