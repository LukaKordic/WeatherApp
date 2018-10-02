package com.lukakordic.weatherapp.data.db

import com.lukakordic.weatherapp.data.db.dao.ForecastDao
import com.lukakordic.weatherapp.data.db.dao.WeatherDao
import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.data.model.WeatherResponse
import org.jetbrains.anko.doAsync

class DbStorageImpl constructor(private val weatherDao: WeatherDao, private val forecastDao: ForecastDao) : DbStorage {

    override fun saveCurrentWeatherData(weather: WeatherResponse) {
        doAsync { weatherDao.storeWeatherResponse(weather) }
    }

    override fun getCurrentWeatherForCity(city: String): WeatherResponse {
        return weatherDao.getWeatherForCity(city) //move this from main thread
    }

    override fun saveCurrentForecastData(forecast: ForecastResponse) {
        doAsync { forecastDao.storeForecastResponse(forecast) }
    }

    override fun getForecastDataForCity(city: String): ForecastResponse {
        return forecastDao.getForecastForCity(city)
    }
}
