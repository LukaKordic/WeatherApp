package com.lukakordic.weatherapp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lukakordic.weatherapp.utils.TITLE_FORECAST
import com.lukakordic.weatherapp.utils.TITLE_WEATHER

class TabsPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = arrayListOf<Fragment>()
    private val titles = arrayListOf(TITLE_WEATHER, TITLE_FORECAST)

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}