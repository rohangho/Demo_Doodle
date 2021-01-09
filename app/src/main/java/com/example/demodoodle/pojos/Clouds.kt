package com.example.sampleweather.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    @Expose
    var all: Int? = null

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param all
     */
    constructor(all: Int?) : super() {
        this.all = all
    }
}