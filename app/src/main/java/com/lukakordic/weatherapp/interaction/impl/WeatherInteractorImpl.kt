package com.lukakordic.weatherapp.interaction.impl

import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.networking.WeatherApiService
import retrofit2.Callback

class WeatherInteractorImpl constructor(private val apiService: WeatherApiService) : WeatherInteractor {

    override fun getWeatherData(cityName: String, callback: Callback<WeatherResponse>) = apiService.getWeatherData(cityName).enqueue(callback)
}
