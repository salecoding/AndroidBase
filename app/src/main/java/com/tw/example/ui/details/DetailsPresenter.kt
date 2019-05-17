package com.tw.example.ui.details

import com.tw.baselibs.mvp.BasePresenter

class DetailsPresenter : BasePresenter<DetailsContract.Model, DetailsContract.View>(), DetailsContract.Presenter {
    override fun createModel(): DetailsContract.Model {
        return DetailsModel()
    }
}
