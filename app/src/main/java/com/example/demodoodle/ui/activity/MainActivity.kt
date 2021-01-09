package com.example.demodoodle.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
import com.example.sampleweather.pojos.Coordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var counter = 0
    var viewPager: ViewPager2? = null
    private lateinit var weatherViewModel: MainViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var coordinates: Coordinates
    private lateinit var toolbar: Toolbar


    private val data: ArrayList<String> = ArrayList()
    private var permissionsToRequest: ArrayList<String>? = null
    private val permissionsRejected: ArrayList<String> = ArrayList()
    private val permissions: ArrayList<String> = ArrayList()

    // integer for permissions results request
    private val ALL_PERMISSIONS_RESULT = 1011

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        permissionsToRequest = permissionsToRequest(permissions)

        if (permissionsToRequest!!.size > 0) {
            requestPermissions(permissionsToRequest!!.toArray(
                    arrayOfNulls<String>(
                            permissionsToRequest!!.size
                    )), ALL_PERMISSIONS_RESULT)
        }

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        weatherViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        weatherViewModel.getWeather().observe(this, {
            updateUI(it)

        })

        getLocation()

    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        coordinates = Coordinates(location.longitude, location.latitude)
                        weatherViewModel.setCityId(coordinates)
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        val cityName: String = addresses[0].countryName
                        toolbar.title = cityName
                        repeatFun().start()

                    } else {
                        AlertDialog.Builder(this@MainActivity).setMessage("We are not getting GPS Connection").setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                            requestPermissions(permissionsRejected.toTypedArray(), ALL_PERMISSIONS_RESULT)
                        }).setNegativeButton("Cancel", null).create().show()
                    }
                }
    }

    private fun permissionsToRequest(wantedPermissions: ArrayList<String>): ArrayList<String>? {
        val result: ArrayList<String> = ArrayList()
        for (perm in wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm)
            }
        }
        return result
    }

    private fun hasPermission(permission: String): Boolean {

        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }


    fun repeatFun(): Job {
        return GlobalScope.launch {
            while (true) {
                sharedViewModel.update(counter++)
                delay(900000)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        repeatFun().cancel()
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
                sharedViewModel.update(counter++)
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
        return ViewPagerAdapter(this, 2, tomorrowWeather, coordinates)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        when (requestCode) {
            ALL_PERMISSIONS_RESULT -> {
                for (perm in permissionsToRequest!!) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm)
                    }
                }
                if (permissionsRejected.size > 0) {
                    if (shouldShowRequestPermissionRationale(permissionsRejected[0])) {
                        AlertDialog.Builder(this@MainActivity).setMessage("These permissions are mandatory to get your location. You need to allow them.").setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                            requestPermissions(permissionsRejected.toTypedArray(), ALL_PERMISSIONS_RESULT)
                        }).setNegativeButton("Cancel", null).create().show()
                        return
                    } else
                        getLocation()
                } else
                    getLocation()
            }
        }
    }


}