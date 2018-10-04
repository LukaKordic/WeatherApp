package com.lukakordic.weatherapp.presentation

import com.lukakordic.weatherapp.ui.view.WeatherView

interface WeatherPresenter {

    fun setView(view: WeatherView)

    fun fetchWeatherDataFromApi(cityName: String)

    fun fetchWeatherDataFromDb(cityName: String)
}
