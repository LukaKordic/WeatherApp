package com.lukakordic.weatherapp.utils.extensions

import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

private const val BASE_WEATHER_IMAGE_URL = "http://api.openweathermap.org/img/w/"
private const val BASE_FORECAST_IMAGE_URL = "http://api.openweathermap.org/img/f/"

fun Fragment.toast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
}

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