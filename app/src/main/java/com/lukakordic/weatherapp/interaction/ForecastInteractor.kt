package com.lukakordic.weatherapp.interaction

import com.lukakordic.weatherapp.data.model.WeatherResponse
import retrofit2.Callback

interface ForecastInteractor {

    fun getForecastData(cityName: String, callback: Callback<WeatherResponse>)
}