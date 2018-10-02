package com.lukakordic.weatherapp.data.db

import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.data.model.WeatherResponse

interface DbStorage {

    fun saveCurrentWeatherData(weather: WeatherResponse)

    fun getCurrentWeatherForCity(city: String): WeatherResponse

    fun saveCurrentForecastData(forecast: ForecastResponse)

    fun getForecastDataForCity(city: String): ForecastResponse
}
