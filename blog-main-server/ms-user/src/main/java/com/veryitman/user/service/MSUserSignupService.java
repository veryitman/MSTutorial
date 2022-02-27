/**
 * @Description：注册服务
 * @author：Mark
 * @CreateDate：2020.12.25
 * @update：[1][2020.12.25][Mark][New]
 */
package com.veryitman.user.service;

import com.veryitman.core.model.MSResponse;

public interface MSUserSignupService {

    /**
     * 用户名/密码注册
     * @param userName 用户名
     * @param pwd 用户设置的密码
     * @return @see MSResponse
     */
    MSResponse signupUsingUserName(String userName, String pwd);
}
