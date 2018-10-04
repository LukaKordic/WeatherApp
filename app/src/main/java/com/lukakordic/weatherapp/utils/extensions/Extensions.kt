package com.lukakordic.weatherapp.utils.extensions

import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

private const val BASE_WEATHER_IMAGE_URL = "http://api.openweathermap.org/img/w/"

//fragment extensions

fun Fragment.toast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
}
//view extensions

fun ImageView.loadWeatherIcon(url: String) {
    Glide.with(this).load(BASE_WEATHER_IMAGE_URL + url).into(this)
}

fun ImageView.loadForecastIcon(url: String) {
    Glide.with(this).load(BASE_WEATHER_IMAGE_URL + url).into(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun IntArray.contains(code: Int): Boolean = any { it == code }