package com.lukakordic.weatherapp.data.db

import com.lukakordic.weatherapp.data.db.dao.WeatherDao
import com.lukakordic.weatherapp.data.model.WeatherResponse
import org.jetbrains.anko.doAsync

class DbStorageImpl constructor(private val weatherDao: WeatherDao) : DbStorage {

    override fun saveCurrentWeather(weather: WeatherResponse) {
        doAsync { weatherDao.storeWeatherResponse(weather) }
    }

    override fun getCurrentWeatherForCity(city: String): WeatherResponse {
       return weatherDao.getWeatherForCity(city)
    }
}
