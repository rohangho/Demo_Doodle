package com.example.demodoodle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.demodoodle.pojos.TodayBaseResponse
import com.example.demodoodle.repository.TodayWeatherRepository
import com.example.sampleweather.pojos.Coordinates

class TodayViewModel(application: Application) : AndroidViewModel(application) {
    private val cityId: MutableLiveData<Coordinates> = MutableLiveData()


    private val weatherData: LiveData<TodayBaseResponse> = Transformations.switchMap(cityId) {
        TodayWeatherRepository().getWeatherResponse(it)
    }

    fun setCityId(latLong: Coordinates) {
        cityId.value = latLong

    }

    fun getTodayWeather(): LiveData<TodayBaseResponse> {
        return weatherData
    }
}