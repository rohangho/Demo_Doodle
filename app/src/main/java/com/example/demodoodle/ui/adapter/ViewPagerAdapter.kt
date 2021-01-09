package com.example.demodoodle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demodoodle.pojos.TomorrowWeather
import com.example.demodoodle.ui.fragment.DisplayTodayFragment
import com.example.demodoodle.ui.fragment.DisplayTomorrowFragment

class ViewPagerAdapter(
        fragmentActivity: FragmentActivity, private val tabCount: Int, private val tomorrowWeather: TomorrowWeather, val cityId: String,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            DisplayTodayFragment(cityId)
        else
            DisplayTomorrowFragment(tomorrowWeather)
    }

}