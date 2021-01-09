package com.example.demodoodle.pojos

import com.example.sampleweather.pojos.Clouds
import com.example.sampleweather.pojos.Coordinates
import com.example.sampleweather.pojos.Weather
import com.example.sampleweather.pojos.Wind
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TodayBaseResponse {
    @SerializedName("coord")
    @Expose
    var coord: Coordinates? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("main")
    @Expose
    var main: TodayMainInfo? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sys")
    @Expose
    var sys: TodaySys? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cod")
    @Expose
    var cod: Int? = null
}