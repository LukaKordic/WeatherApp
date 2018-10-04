package com.lukakordic.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun hasNetworkAccess(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnected
    }
}