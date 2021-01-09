package com.example.sampleweather.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Double? = null

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param deg
     * @param speed
     */
    constructor(speed: Double?, deg: Double?) : super() {
        this.speed = speed
        this.deg = deg
    }
}