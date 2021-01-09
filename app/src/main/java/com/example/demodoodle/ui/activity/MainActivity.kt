package com.example.demodoodle.ui.activity

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.demodoodle.R
import com.example.demodoodle.pojos.BaseResponseForAllDay
import com.example.demodoodle.pojos.TomorrowWeather
import com.example.demodoodle.ui.adapter.ViewPagerAdapter
import com.example.demodoodle.viewModel.MainViewModel
import com.example.demodoodle.viewModel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var counter = 0
    var viewPager: ViewPager2? = null
    private lateinit var weatherViewModel: MainViewModel
    private lateinit var sharedViewModel: SharedViewModel

    private val data: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)



        weatherViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        weatherViewModel.setCityId("529334")

        weatherViewModel.getWeather().observe(this, {
            updateUI(it)

        })

    }

    private fun updateUI(baseResponseForAllDay: BaseResponseForAllDay) {
        var abc: ArrayList<String> = ArrayList()
        val tomorrowWeather = TomorrowWeather(baseResponseForAllDay.list!![1].main?.temp,
                baseResponseForAllDay.list!![1].main?.humidity, baseResponseForAllDay.list!![1].main?.tempMin, baseResponseForAllDay.list!![1].main?.tempMax,
                baseResponseForAllDay.list!![1].main?.feelsLike)
        for (i in 0..1) {
            abc.add("http://openweathermap.org/img/wn/" + baseResponseForAllDay.list!![i].weather!![0].icon + "@2x" + ".png")
        }
        viewPager!!.adapter = createCardAdapter(tomorrowWeather)
        showTabs(abc)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh ->
                sharedViewModel.update(counter)
        }
        return true

    }


    private fun showTabs(abc: ArrayList<String>) {
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

        for (i in data.indices) {
            tabLayout!!.addTab(tabLayout!!.newTab().setText(data[i]))
        }
        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, pos ->
            when (pos) {
                pos -> {
                    Glide.with(this).asDrawable().load(abc[pos])
                            .into(object : CustomTarget<Drawable?>() {
                                override fun onResourceReady(
                                        resource: Drawable,
                                        transition: Transition<in Drawable?>?
                                ) {
                                    tab.icon = resource
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {

                                }

                            })
                    tab.text = data[pos]
                }
            }
        }.attach()

    }

    private fun createCardAdapter(tomorrowWeather: TomorrowWeather): ViewPagerAdapter? {
        return ViewPagerAdapter(this, 2, tomorrowWeather, "529334")
    }
}