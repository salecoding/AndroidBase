package com.tw.example.ui.details

import com.tw.baselibs.mvp.IModel
import com.tw.baselibs.mvp.IPresenter
import com.tw.baselibs.mvp.IView

interface DetailsContract {

    interface View : IView {
    }

    interface Presenter : IPresenter<View> {
    }

    interface Model : IModel {
    }
}