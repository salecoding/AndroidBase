package com.tw.example.ui.main

import com.tw.baselibs.mvp.BaseModel
import com.tw.baselibs.net.BaseHttpResult
import com.tw.example.bean.QueryWhere
import com.tw.example.data.entity.TestEntity
import com.tw.example.data.repository.RetrofitUtils
import io.reactivex.Observable


class MainModel : BaseModel(), MainContract.Model {

    override fun getTestData(queryWhere: QueryWhere): Observable<BaseHttpResult<List<TestEntity>>> {
        return RetrofitUtils.service.wholeCityRanking(queryWhere)
    }
}