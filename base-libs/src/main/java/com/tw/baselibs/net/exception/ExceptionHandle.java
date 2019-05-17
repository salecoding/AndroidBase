package com.tw.baselibs.net.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class ExceptionHandle {
    public static String handleException(Throwable e) {
        String errorMsg;
        if (e instanceof SocketTimeoutException) {//网络超时
            errorMsg = "网络连接异常";
        } else if (e instanceof ConnectException) { //均视为网络错误
            errorMsg = "网络连接异常";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //均视为解析错误
            errorMsg = "数据解析异常";
        } else if (e instanceof UnknownHostException) {
            errorMsg = "网络连接异常";
        } else if (e instanceof IllegalArgumentException) {
            errorMsg = "参数错误";
        } else {//未知错误
            errorMsg = e.getMessage().toLowerCase();
        }
        return errorMsg;
    }
}
