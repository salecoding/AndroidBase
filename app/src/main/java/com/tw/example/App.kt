package com.tw.example

import com.tw.baselibs.app.BaseApp
import org.litepal.LitePal

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
    }
}
