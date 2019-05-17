package com.tw.example.data.repository

import com.tw.baselibs.net.BaseRetrofit
import com.tw.example.data.api.ApiService

/**
 * @author salecoding
 * @date 2018/6/11 23:02
 * @desc 网络请求管理类
 */
object RetrofitUtils : BaseRetrofit() {
    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)
    }
}
