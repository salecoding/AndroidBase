package com.tw.example.db

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

class Song : LitePalSupport() {

    @Column(nullable = false)
    val name: String? = null

    val duration: Int = 0

    @Column(ignore = true)
    val uselessField: String? = null

    val album: Album? = null
}
