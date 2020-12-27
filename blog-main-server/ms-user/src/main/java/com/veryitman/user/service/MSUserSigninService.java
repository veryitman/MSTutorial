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

    MSResponse signinUsingToken(String userName, String token);
}
