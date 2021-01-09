package com.example.demodoodle.ui.activity

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Menu
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.demodoodle.R
import com.example.demodoodle.ViewModel.DisplayViewModel
import com.example.demodoodle.pojos.BaseResponse
import com.example.demodoodle.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager2? = null
    private lateinit var weatherViewModel: DisplayViewModel

    private val data: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)



        weatherViewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
        weatherViewModel.setCityId("707860")

        weatherViewModel.getWeather().observe(this, {
            viewPager!!.adapter = createCardAdapter(it)
            showTabs()
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }


    private fun showTabs() {
        data.add("Today")
        data.add("Tomorrow")
        val root = tabLayout!!.getChildAt(0)
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout!!.tabMode = TabLayout.MODE_FIXED
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.black))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        for (i in data!!.indices) {
            tabLayout!!.addTab(tabLayout!!.newTab().setText(data[i]))
        }
        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, pos ->
            when (pos) {
                pos -> {
                    tab.text = data[pos]
                }
            }
        }.attach()

    }

    private fun createCardAdapter(baseResponse: BaseResponse): ViewPagerAdapter? {
        return ViewPagerAdapter(this, 2)
    }
}