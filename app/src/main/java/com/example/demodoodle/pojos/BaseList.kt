package com.example.demodoodle.pojos

import com.example.sampleweather.pojos.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseList {
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("main")
    @Expose
    var main: MainInformation? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("pop")
    @Expose
    var pop: Int? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("snow")
    @Expose
    var snow: Snow? = null
}