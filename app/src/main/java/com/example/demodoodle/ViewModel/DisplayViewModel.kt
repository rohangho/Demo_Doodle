package com.example.demodoodle.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.demodoodle.pojos.BaseResponse
import com.example.demodoodle.repository.WeatherRepository

class DisplayViewModel(application: Application) : AndroidViewModel(application) {
    private val date: MutableLiveData<String> = MutableLiveData()


    private val weatherData: LiveData<BaseResponse> = Transformations.switchMap(date) {
        WeatherRepository().getWeatherResponse(it)
    }

    fun setCityId(id: String) {
        if (date.value == id) {
            return
        }
        date.value = id

    }

    fun getWeather(): LiveData<BaseResponse> {
        return weatherData
    }
}