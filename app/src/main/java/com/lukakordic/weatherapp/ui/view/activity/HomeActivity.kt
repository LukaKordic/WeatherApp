package com.lukakordic.weatherapp.ui.view.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.ui.adapter.TabsPageAdapter
import com.lukakordic.weatherapp.ui.view.fragment.ForecastFragment
import com.lukakordic.weatherapp.ui.view.fragment.WeatherFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initUI()
        cityName.text = "Weather App"
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
        }
    }
}
