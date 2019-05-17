package com.tw.example.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TestEntity() : Parcelable {
    /**
     * ROWNO : 1
     * ZFACTORYID : 0
     * ZFACTORYNAME : null
     * TOTAL : 2
     * UNTREATED : 0
     * PROCESSED : 0
     * OVERDUE : 2
     */
    @SerializedName("ROWNO")
    var rowno: Int = 0
    @SerializedName("ZFACTORYID")
    var zfactoryid: Int = 0
    @SerializedName("ZFACTORYNAME")
    var zfactoryname: String? = null
    @SerializedName("TOTAL")
    var total: Int = 0
    @SerializedName("UNTREATED")
    var untreated: Int = 0
    @SerializedName("PROCESSED")
    var processed: Int = 0
    @SerializedName("OVERDUE")
    var overdue: Int = 0

    constructor(parcel: Parcel) : this() {
        rowno = parcel.readInt()
        zfactoryid = parcel.readInt()
        zfactoryname = parcel.readString()
        total = parcel.readInt()
        untreated = parcel.readInt()
        processed = parcel.readInt()
        overdue = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(rowno)
        parcel.writeInt(zfactoryid)
        parcel.writeString(zfactoryname)
        parcel.writeInt(total)
        parcel.writeInt(untreated)
        parcel.writeInt(processed)
        parcel.writeInt(overdue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestEntity> {
        override fun createFromParcel(parcel: Parcel): TestEntity {
            return TestEntity(parcel)
        }

        override fun newArray(size: Int): Array<TestEntity?> {
            return arrayOfNulls(size)
        }
    }
}
