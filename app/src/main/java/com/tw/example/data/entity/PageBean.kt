package com.tw.example.data.entity

open class PageBean {
    var pageIndex = 1
    var pageSize = 15
    var isRefresh = true
        set(refresh) {
            field = refresh
            pageIndex = if (isRefresh)
                1
            else
                ++pageIndex
        }
}
