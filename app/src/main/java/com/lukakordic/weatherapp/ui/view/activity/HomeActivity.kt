package com.lukakordic.weatherapp.ui.view.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.ui.adapter.TabsPageAdapter
import com.lukakordic.weatherapp.ui.view.PassCityInterface
import com.lukakordic.weatherapp.ui.view.fragment.ForecastFragment
import com.lukakordic.weatherapp.ui.view.fragment.WeatherFragment
import com.lukakordic.weatherapp.utils.constants.CITY_NAME
import com.lukakordic.weatherapp.utils.extensions.hide
import com.lukakordic.weatherapp.utils.extensions.show
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity(), PassCityInterface {

    private lateinit var cityName: String
    override fun passCity(city: String) {
        cityName = city
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initUI()
        toolbarTitle.text = getString(R.string.toolbar_title)
    }

    private fun initUI() {
        setupViewPager(viewPager)
        pagerTabs.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val tabsPageAdapter = TabsPageAdapter(supportFragmentManager)
        with(tabsPageAdapter) {
            addFragment(WeatherFragment.getInstance())
            addFragment(ForecastFragment.getInstance())
            viewPager.adapter = this
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {}

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

                override fun onPageSelected(p0: Int) {
                    when (p0) {
                        0 -> refresh.show()
                        1 -> {
                            val forecastFragment = getItem(1) as ForecastFragment
                            refresh.hide()
                            forecastFragment.storeCity(cityName)
                        }
                    }
                }
            })
        }
    }
}
