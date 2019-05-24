package com.tw.example.db

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

class Person : LitePalSupport() {
    @Column(unique = true, defaultValue = "unknown")
    var name: String? = null

    var price: Int = 0
}