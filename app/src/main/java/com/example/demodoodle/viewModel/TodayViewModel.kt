package com.example.demodoodle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.demodoodle.pojos.TodayBaseResponse
import com.example.demodoodle.repository.TodayWeatherRepository

class TodayViewModel(application: Application) : AndroidViewModel(application) {
    private val cityId: MutableLiveData<String> = MutableLiveData()


    private val weatherData: LiveData<TodayBaseResponse> = Transformations.switchMap(cityId) {
        TodayWeatherRepository().getWeatherResponse(it)
    }

    fun setCityId(id: String) {
        if (cityId.value == id) {
            return
        }
        cityId.value = id

    }

    fun getTodayWeather(): LiveData<TodayBaseResponse> {
        return weatherData
    }
}