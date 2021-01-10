package com.example.demodoodle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.demodoodle.R
import com.example.demodoodle.databinding.FragmentTomorrowBinding
import com.example.demodoodle.pojos.TomorrowWeather
import java.text.DecimalFormat

class DisplayTomorrowFragment(var info: TomorrowWeather) : Fragment() {

    private val df2: DecimalFormat = DecimalFormat("#.##")
    private lateinit var binding: FragmentTomorrowBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tomorrow, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.minTemp.text = (df2.format(info.minTemp!! - 273)).toString() + " \u2103"
        binding.maxTemp.text = (df2.format(info.maxTemp!! - 273)).toString() + " \u2103"
        binding.avgTemp.text = (df2.format(info.temperature!! - 273)).toString() + " \u2103"
        binding.humidityLevel.text = "Humidity Level " + info.humidity.toString()


    }


}