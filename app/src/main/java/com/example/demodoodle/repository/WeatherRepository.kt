package com.example.demodoodle.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demodoodle.pojos.BaseResponseForAllDay
import com.example.sampleweather.network.NetworkAPI
import com.example.sampleweather.network.RetrofitService.createService
import com.example.sampleweather.pojos.Coordinates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherRepository {

    private var networkAPI: NetworkAPI? = null

    init {
        networkAPI = createService(NetworkAPI::class.java)
    }


    fun getWeatherResponse(cityId: Coordinates): MutableLiveData<BaseResponseForAllDay?>? {
        val listWeather = MutableLiveData<BaseResponseForAllDay?>()
        networkAPI!!.getListWeather(
            cityId.lat.toString(),
            cityId.lon.toString(),
            "19c582ab57628fee373c6c741f78d8d8"
        )
            .enqueue(object : Callback<BaseResponseForAllDay> {
                override fun onResponse(
                    call: Call<BaseResponseForAllDay>,
                    responseForAllDay: Response<BaseResponseForAllDay>
                ) {
                    if (responseForAllDay.isSuccessful)
                        listWeather.value = responseForAllDay.body()
                }

                override fun onFailure(call: Call<BaseResponseForAllDay>, t: Throwable) {
                    Log.e("Api Issue", t.localizedMessage!!)
                }

            })
        return listWeather
    }
}