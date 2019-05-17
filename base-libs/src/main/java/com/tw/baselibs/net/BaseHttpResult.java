package com.tw.baselibs.net;

import com.google.gson.annotations.SerializedName;

/**
 * @author salecoding
 * @date 2018/6/12 00:58
 * @desc 抽取的一个基类的bean, 直接在泛型中传data就行
 */
public class BaseHttpResult<T> {
    public static final int Ok = 0;
    public static final int Login_Failure = 1001;
    public static final int Login_UserIdOrPassword_Invalid = 1002;
    public static final int Login_User_Disabled = 1003;
    public static final int Auth_No_Token = 1004;
    public static final int Auth_Token_Invalid = 1005;
    public static final int Auth_Token_Expired = 1006;
    public static final int Failure = 1007;

    @SerializedName("Code")
    private int code;
    @SerializedName("Msg")
    private String msg;
    @SerializedName("Data")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        data = data;
    }

    /**
     * 正常返回
     *
     * @return
     */
    public boolean isSuccessFul() {
        return getCode() == Ok;
    }
}
