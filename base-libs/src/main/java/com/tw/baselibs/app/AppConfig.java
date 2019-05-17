package com.tw.baselibs.app;

/**
 * @author salecoding
 * @date 2018/6/11 17:34
 * @desc APP 的配置参数
 */
public class AppConfig {

    public static final String BASE_URL = "http://192.168.19.128:8089/Api/";

    /**
     * 用户配置
     */
    public static class UserConfig {
        public static final String USER_SP = "sp_user";
        public static final String USERNAME = "userid";
        public static final String PASSWORD = "password";
        public static final String REMEMBER_PASSWORD = "rememberPassword";
    }

    /**
     * Token配置信息
     */
    public class TokenConfig {
        public static final String TOKEN_URL = "Auth/GetSuperviseToken";

        public static final String TOKEN_KEY = "Token";

    }
}
