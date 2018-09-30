package com.lukakordic.weatherapp.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.utils.WEATHER_TABLE

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeWeatherResponse(responseToStore: WeatherResponse)

    @Query("SELECT * FROM $WEATHER_TABLE WHERE name = :city LIMIT 1")
    fun getWeatherForCity(city: String): WeatherResponse
}
