package com.example.sampleweather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}