package com.example.sampleweather.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates(
    @field:Expose @field:SerializedName("lon") var lon: Double,
    @field:Expose @field:SerializedName("lat") var lat: Double
)