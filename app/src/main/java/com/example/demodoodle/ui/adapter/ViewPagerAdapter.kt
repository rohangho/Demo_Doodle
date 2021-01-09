package com.example.demodoodle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demodoodle.ui.fragment.DisplayFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity, private val tabCount: Int,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return DisplayFragment(position)
    }

}