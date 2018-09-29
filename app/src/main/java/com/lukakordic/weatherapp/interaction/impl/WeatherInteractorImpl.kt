package com.lukakordic.weatherapp.interaction.impl

import com.lukakordic.weatherapp.data.response.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.networking.WeatherApiService
import retrofit2.Callback
import javax.inject.Inject

class WeatherInteractorImpl @Inject constructor(private val apiService: WeatherApiService) : WeatherInteractor {

    override fun getWeatherData(cityName: String, callback: Callback<WeatherResponse>) = apiService.getWeatherData(cityName).enqueue(callback)
}