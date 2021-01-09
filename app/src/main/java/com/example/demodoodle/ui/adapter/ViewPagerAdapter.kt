package com.example.demodoodle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demodoodle.pojos.TomorrowWeather
import com.example.demodoodle.ui.fragment.DisplayTodayFragment
import com.example.demodoodle.ui.fragment.DisplayTomorrowFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity, private val tabCount: Int,val tomorrowWeather: TomorrowWeather,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0)
            DisplayTodayFragment(position)
        else
            DisplayTomorrowFragment(tomorrowWeather)
    }

}