package com.tw.example.ui.main

import com.tw.baselibs.mvp.IModel
import com.tw.baselibs.mvp.IPresenter
import com.tw.baselibs.mvp.IView
import com.tw.baselibs.net.BaseHttpResult
import com.tw.example.data.entity.QueryWhere
import com.tw.example.data.entity.TestEntity
import io.reactivex.Observable

interface MainContract {

    interface View : IView {
        fun showData(testEntityList: List<TestEntity>)
    }

    interface Presenter : IPresenter<View> {
        fun requestTestData(queryWhere: QueryWhere)
    }

    interface Model : IModel {
        fun getTestData(queryWhere: QueryWhere): Observable<BaseHttpResult<List<TestEntity>>>
    }
}