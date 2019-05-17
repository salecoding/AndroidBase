package com.tw.example.ui.main

import com.tw.baselibs.mvp.BasePresenter
import com.tw.baselibs.net.BaseHandleFuc
import com.tw.baselibs.net.exception.ExceptionHandle
import com.tw.baselibs.rx.RxSchedulers
import com.tw.example.bean.QueryWhere
import com.tw.example.data.entity.TestEntity

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {
    override fun createModel(): MainContract.Model {
        return MainModel()
    }

    override fun requestTestData(queryWhere: QueryWhere) {
        if (queryWhere.isRefresh)
            view.showLoading()
        val disposable = model.getTestData(queryWhere)
                .map(BaseHandleFuc<List<TestEntity>>())
                .compose(RxSchedulers.applySchedulers())
                .subscribe({ result ->
                    view.hideLoading()
                    view.showData(result)
                }) { throwable ->
                    view.hideLoading()
                    view.showFail(ExceptionHandle.handleException(throwable))
                }
        addDispose(disposable)
    }

}