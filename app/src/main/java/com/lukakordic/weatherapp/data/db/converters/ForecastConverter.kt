package com.lukakordic.weatherapp.data.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lukakordic.weatherapp.data.model.Forecast
import java.lang.reflect.Type

class ForecastConverter {

    private val gson = Gson()

    val type: Type = object : TypeToken<List<Forecast>>() {}.type

    @TypeConverter
    fun forecastListToString(listToConvert: List<Forecast>) = gson.toJson(listToConvert, type)

    @TypeConverter
    fun stringToForecastList(stringToConvert: String) = gson.fromJson<List<Forecast>>(stringToConvert, type)
}