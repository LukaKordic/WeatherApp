package com.lukakordic.weatherapp.ui.view

import com.lukakordic.weatherapp.data.response.WeatherResponse

interface WeatherView {

    fun showWeather(weather: WeatherResponse)

    fun showNetworkError(error: Throwable)
}