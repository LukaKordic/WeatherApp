package com.lukakordic.weatherapp.data.db

import com.lukakordic.weatherapp.data.db.dao.WeatherDao
import com.lukakordic.weatherapp.data.model.WeatherResponse

class DbStorageImpl constructor(private val weatherDao: WeatherDao) : DbStorage {

    override fun saveCurrentWeather(weather: WeatherResponse) = weatherDao.storeWeatherResponse(weather)

    override fun getCurrentWeatherForCity(city: String): WeatherResponse = weatherDao.getWeatherForCity(city)
}
