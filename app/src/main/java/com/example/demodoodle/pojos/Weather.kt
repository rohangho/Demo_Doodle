package com.example.sampleweather.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param icon
     * @param description
     * @param main
     * @param id
     */
    constructor(id: Int?, main: String?, description: String?, icon: String?) : super() {
        this.id = id
        this.main = main
        this.description = description
        this.icon = icon
    }
}