package com.lukakordic.weatherapp.interaction

import com.lukakordic.weatherapp.data.model.WeatherResponse
import retrofit2.Callback

interface WeatherInteractor {

    fun getWeatherData(cityName: String, callback: Callback<WeatherResponse>)
}