package com.example.demodoodle.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponseForAllDay {
    @SerializedName("cod")
    @Expose
    var cod: String? = null

    @SerializedName("message")
    @Expose
    var message: Int? = null

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null

    @SerializedName("list")
    @Expose
    var list: List<BaseList>? = null

    @SerializedName("city")
    @Expose
    var city: City? = null
}