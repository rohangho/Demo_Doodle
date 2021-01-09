package com.example.demodoodle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.demodoodle.pojos.BaseResponseForAllDay
import com.example.demodoodle.repository.WeatherRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val cityId: MutableLiveData<String> = MutableLiveData()


    private val weatherData: LiveData<BaseResponseForAllDay> = Transformations.switchMap(cityId) {
        WeatherRepository().getWeatherResponse(it)
    }

    fun setCityId(id: String) {
        if (cityId.value == id) {
            return
        }
        cityId.value = id

    }

    fun getWeather(): LiveData<BaseResponseForAllDay> {
        return weatherData
    }
}