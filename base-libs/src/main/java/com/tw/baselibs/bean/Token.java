package com.tw.baselibs.bean;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2018/12/19
 */
public class Token {
    private String token;
    private long expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token, long expire) {
        this.token = token;
        this.expire = expire;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
