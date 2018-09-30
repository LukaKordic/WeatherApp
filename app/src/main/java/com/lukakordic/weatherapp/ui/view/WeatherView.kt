package com.lukakordic.weatherapp.ui.view

interface WeatherView {

    fun showCityName(city: String)

    fun showWeatherIcon(icon: String)

    fun showNetworkError(error: Throwable)
}