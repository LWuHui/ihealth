package com.kiwi.upms.busine;

import com.kiwi.upms.bo.user.UserInfoBO;

/**
 * @author jenny
 * @since 2019/7/30 22:21
 */
public interface UpmsUserBusiness {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息BO
     */
    UserInfoBO getUserByUserName(String userName);
}
