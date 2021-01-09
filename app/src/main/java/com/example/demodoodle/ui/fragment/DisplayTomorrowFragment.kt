package com.example.demodoodle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.demodoodle.R
import com.example.demodoodle.pojos.TomorrowWeather
import java.text.DecimalFormat

class DisplayTomorrowFragment(var info: TomorrowWeather) : Fragment() {


    private lateinit var minTemperature: TextView
    private lateinit var maxTemperature: TextView
    private lateinit var avgTemperatue: TextView
    private lateinit var humidity: TextView
    private val df2: DecimalFormat = DecimalFormat("#.##")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_tomorrow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        minTemperature = view.findViewById(R.id.minTemp)
        maxTemperature = view.findViewById(R.id.maxTemp)
        avgTemperatue = view.findViewById(R.id.avgTemp)
        humidity = view.findViewById(R.id.humidityLevel)

        minTemperature.text = (df2.format(info.minTemp!! - 273)).toString() + " \u2103"
        maxTemperature.text = (df2.format(info.maxTemp!! - 273)).toString() + " \u2103"
        avgTemperatue.text = (df2.format(info.temperature!! - 273)).toString() + " \u2103"
        humidity.text = "Humidity Level " + info.humidity.toString()


    }


}