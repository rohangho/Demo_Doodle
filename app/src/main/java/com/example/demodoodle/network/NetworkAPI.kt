package com.example.sampleweather.network

import com.example.demodoodle.pojos.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {
    @GET("2.5/forecast")
    fun getListWeather(@Query("id") id: String?, @Query("appid") key: String?): Call<BaseResponse>
}