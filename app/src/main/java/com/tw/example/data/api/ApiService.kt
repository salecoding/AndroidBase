package com.tw.example.data.api

import com.tw.baselibs.net.BaseHttpResult
import com.tw.example.data.entity.QueryWhere
import com.tw.example.data.entity.TestEntity
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    /**
     * 全市巡检排名
     *
     * @return
     */
    @POST("Data/WholeCityRanking")
    fun wholeCityRanking(@Body queryWhere: QueryWhere): Observable<BaseHttpResult<List<TestEntity>>>

}
