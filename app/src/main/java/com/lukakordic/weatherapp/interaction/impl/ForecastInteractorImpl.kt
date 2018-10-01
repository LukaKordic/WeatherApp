package com.lukakordic.weatherapp.interaction.impl

import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.interaction.ForecastInteractor
import com.lukakordic.weatherapp.networking.WeatherApiService
import retrofit2.Callback

class ForecastInteractorImpl constructor(private val apiService: WeatherApiService) : ForecastInteractor {

    override fun getForecastData(cityName: String, callback: Callback<WeatherResponse>) = apiService.getForecastData(cityName).enqueue(callback)
}