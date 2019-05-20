package com.tw.example.data.entity

open class PageBean {
    var pageIndex = 1
    var pageSize = 15
    var isRefresh = true
        set(refresh) {
            field = refresh
            if (isRefresh)
                pageIndex = 1
            else
                pageIndex = ++pageIndex
        }
}
