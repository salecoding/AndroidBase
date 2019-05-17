package com.tw.baselibs.net.token;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.tw.baselibs.app.AppConfig;
import com.tw.baselibs.bean.Token;
import com.tw.baselibs.cache.CacheManager;
import com.tw.baselibs.net.BaseHttpResult;
import com.tw.baselibs.utils.GsonUtils;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Token失效的处理方案二，如果服务端没有遵循设计规范，可以尝试使用如下方法
 * 使用方法：addInterceptor(new TokenInterceptor());
 */
public class TokenInterceptor implements Interceptor {

    private static final int TOKEN_EXPIRE_TIME = 60 * 60 * 2;

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = CacheManager.getString(AppConfig.TokenConfig.TOKEN_KEY);
        Request request = null;
        /**根据和服务端的约定判断token过期,直连服务器才需要Token*/
        if (StringUtils.isEmpty(token)) {
            //同步请求方式，获取最新的Token
            token = getNewToken();
            CacheManager.putString(AppConfig.TokenConfig.TOKEN_KEY, token, TOKEN_EXPIRE_TIME);
            //使用新的Token，创建新的请求
            request = chain.request()
                    .newBuilder()
                    .header(AppConfig.TokenConfig.TOKEN_KEY, token)
                    .build();
            //重新请求
            return chain.proceed(request);
        }
        request = chain.request()
                .newBuilder()
                .header(AppConfig.TokenConfig.TOKEN_KEY, token)
                .build();
        return chain.proceed(request);
    }


    private String getNewToken() throws IOException {
        String newToken;
        String username = SPUtils.getInstance(AppConfig.UserConfig.USER_SP).getString(AppConfig.UserConfig.USERNAME);
        String password = SPUtils.getInstance(AppConfig.UserConfig.USER_SP).getString(AppConfig.UserConfig.PASSWORD);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            username = "admin";
            password = "123456";
        }
        RequestBody body = new FormBody.Builder()
                .add(AppConfig.UserConfig.USERNAME, username)
                .add(AppConfig.UserConfig.PASSWORD, password)
                .build();
        Request request = new Request.Builder()
                .url(AppConfig.BASE_URL + AppConfig.TokenConfig.TOKEN_URL)
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        String json = response.body().string();
        Type type = new TypeToken<BaseHttpResult<Token>>() {
        }.getType();
        BaseHttpResult<Token> result = GsonUtils.convertObj(json, type);
        if (result.isSuccessFul()) {
            Token token = result.getData();
            newToken = token.getToken();
            CacheManager.putString(AppConfig.TokenConfig.TOKEN_KEY, newToken, TOKEN_EXPIRE_TIME);
            return newToken;
        } else {
            throw new RuntimeException(result.getMsg());
        }
    }
}
