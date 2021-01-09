package com.example.demodoodle.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demodoodle.pojos.TodayBaseResponse
import com.example.sampleweather.network.NetworkAPI
import com.example.sampleweather.network.RetrofitService.createService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TodayWeatherRepository {

    private var networkAPI: NetworkAPI? = null

    init {
        networkAPI = createService(NetworkAPI::class.java)
    }


    fun getWeatherResponse(cityId: String?): MutableLiveData<TodayBaseResponse?>? {
        val listWeather = MutableLiveData<TodayBaseResponse?>()
        networkAPI!!.getCurrent(cityId, "19c582ab57628fee373c6c741f78d8d8")
                .enqueue(object : Callback<TodayBaseResponse> {
                    override fun onResponse(
                            call: Call<TodayBaseResponse>,
                            todayBaseResponse: Response<TodayBaseResponse>
                    ) {
                        if (todayBaseResponse.isSuccessful)
                            listWeather.value = todayBaseResponse.body()
                    }

                    override fun onFailure(call: Call<TodayBaseResponse>, t: Throwable) {
                        Log.e("Api Issue", t.localizedMessage!!)
                    }

                })
        return listWeather
    }
}