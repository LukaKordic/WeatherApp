package com.lukakordic.weatherapp.data.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lukakordic.weatherapp.data.model.Weather
import java.lang.reflect.Type

class WeatherConverter {

    private val gson = Gson()

    val type: Type = object : TypeToken<List<Weather>>() {}.type

    @TypeConverter
    fun weatherListToString(listToConvert: List<Weather>) = gson.toJson(listToConvert, type)

    @TypeConverter
    fun stringToWeatherList(stringToConvert: String) = gson.fromJson<List<Weather>>(stringToConvert, type)
}