package com.cclu.kkgh.constant;

/**
 * @author ChangCheng Lu
 * @date 2023/8/1 17:27
 */
public interface UserConstant {

    /**
     * 用户密码盐值
     */
    String USER_SALT = "salt";

    /**
     * 用户账号长度限制
     */
    int USER_ACCOUNT_MIN_LENGTH = 4;

    /**
     * 用户密码长度限制
     */
    int USER_PASSWORD_MIN_LENGTH = 8;

    /**
     * 用户登录键
     */
    String USER_LOGIN_STATE = "user_login";

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

}

