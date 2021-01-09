package com.example.sampleweather.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("pod")
    @Expose
    var pod: String? = null

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param pod
     */
    constructor(pod: String?) : super() {
        this.pod = pod
    }
}