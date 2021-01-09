package com.example.demodoodle.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demodoodle.pojos.BaseResponse
import com.example.sampleweather.network.NetworkAPI
import com.example.sampleweather.network.RetrofitService.createService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherRepository {

    private var weatherRepository: WeatherRepository? = null
    private var networkAPI: NetworkAPI? = null

    init {
        networkAPI = createService(NetworkAPI::class.java)
    }

    fun getInstance(): WeatherRepository? {
        if (weatherRepository == null) {
            weatherRepository = WeatherRepository()
        }
        return weatherRepository
    }

    fun getWeatgerresponse(cityId: String?): MutableLiveData<BaseResponse?>? {
        val listWeather = MutableLiveData<BaseResponse?>()
        networkAPI!!.getListWeather(cityId, "19c582ab57628fee373c6c741f78d8d8").enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful)
                    listWeather.value = response.body()
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.e("Api Issue", t.localizedMessage!!)
            }

        })
        return listWeather
    }
}