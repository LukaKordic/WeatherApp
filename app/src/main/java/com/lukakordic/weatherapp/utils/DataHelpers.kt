package com.lukakordic.weatherapp.utils

import android.content.Context
import android.location.Geocoder
import java.util.*

object DataHelpers {

    fun kelvinToCelsius(tempInKelvin: Double): Double = tempInKelvin - 273.15

    fun getAddress(lon: Double, lat: Double, context: Context): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lon, 1)
        return addresses[0].locality
    }
}