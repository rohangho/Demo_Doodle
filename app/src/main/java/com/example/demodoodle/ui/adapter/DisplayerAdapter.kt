package com.example.demodoodle.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demodoodle.R
import com.example.demodoodle.pojos.TodayResponse
import java.text.DecimalFormat

class DisplayerAdapter(private val todayWeather: ArrayList<TodayResponse>) : RecyclerView.Adapter<DisplayerAdapter.MyViewHolder>() {

    private val df2: DecimalFormat = DecimalFormat("#.##")


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var minTemperature: TextView = itemView.findViewById(R.id.minTemp)
        var maxTemperature: TextView = itemView.findViewById(R.id.maxTemp)
        var avgTemperatue: TextView = itemView.findViewById(R.id.avgTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayerAdapter.MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.inside_today,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: DisplayerAdapter.MyViewHolder, position: Int) {
        holder.minTemperature.text = (df2.format(todayWeather[position].minTemp!! - 273)).toString() + " \u2103"
        holder.maxTemperature.text = (df2.format(todayWeather[position].maxTemp!! - 273)).toString() + " \u2103"
        holder.avgTemperatue.text = (df2.format(todayWeather[position].temperature!! - 273)).toString() + " \u2103"
    }

    override fun getItemCount(): Int {
        return todayWeather.size
    }

}