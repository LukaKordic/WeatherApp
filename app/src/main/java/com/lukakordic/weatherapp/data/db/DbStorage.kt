package com.lukakordic.weatherapp.data.db

import com.lukakordic.weatherapp.data.model.WeatherResponse

interface DbStorage {

    fun saveCurrentWeather(weather: WeatherResponse)

    fun getCurrentWeatherForCity(city: String): WeatherResponse
}
