package com.tw.example.db

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport
import java.util.*

class Album : LitePalSupport() {
    @Column(unique = true, defaultValue = "unknown")
    var name: String? = null

    var price: Float = 0.toFloat()

    var cover: ByteArray? = null

    var songs = ArrayList<Song>()

}
