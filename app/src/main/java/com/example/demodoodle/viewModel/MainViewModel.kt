package com.example.demodoodle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.demodoodle.pojos.BaseResponseForAllDay
import com.example.demodoodle.repository.WeatherRepository
import com.example.sampleweather.pojos.Coordinates

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val cityId: MutableLiveData<Coordinates> = MutableLiveData()


    private val weatherData: LiveData<BaseResponseForAllDay> = Transformations.switchMap(cityId) {
        WeatherRepository().getWeatherResponse(it)
    }

    fun setCityId(latLong: Coordinates) {
        if (cityId.value == latLong) {
            return
        }
        cityId.value = latLong

    }

    fun getWeather(): LiveData<BaseResponseForAllDay> {
        return weatherData
    }
}