package com.example.demodoodle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val commoRefreshCounter: MutableLiveData<Int> = MutableLiveData()

    fun update(item: Int) {
        commoRefreshCounter.value = item
    }

    fun getRefreshStatus(): MutableLiveData<Int> {
        return commoRefreshCounter
    }

}