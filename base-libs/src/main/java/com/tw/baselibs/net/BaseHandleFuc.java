package com.tw.baselibs.net;

import io.reactivex.functions.Function;

public class BaseHandleFuc<T> implements Function<BaseHttpResult<T>, T> {
    @Override
    public T apply(BaseHttpResult<T> response) throws Exception {
        if (response.getCode() != BaseHttpResult.Ok) {
            if (response.getCode() == BaseHttpResult.Auth_No_Token || response.getCode() == BaseHttpResult.Auth_Token_Invalid ||
                    response.getCode() == BaseHttpResult.Auth_Token_Expired) {
                //抛出异常，把状态码及状态描述信息传入
                throw new RuntimeException(response.getCode() + "\t" + response.getMsg() != null ? response.getMsg() : "");
            } else if (response.getCode() == BaseHttpResult.Login_UserIdOrPassword_Invalid) {
                //抛出异常，把状态码及状态描述信息传入
                throw new RuntimeException(response.getCode() + "\t" + "账号或密码不正确，请重新登录");
            }
            //抛出异常，把状态码及状态描述信息传入
            throw new RuntimeException(response.getCode() + "\t" + response.getMsg() != null ? response.getMsg() : "");
        }
        return response.getData();
    }
}
