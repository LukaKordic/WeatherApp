package com.lukakordic.weatherapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.lukakordic.weatherapp.data.db.converters.WeatherConverter
import com.lukakordic.weatherapp.data.db.dao.WeatherDao
import com.lukakordic.weatherapp.data.model.WeatherResponse

@Database(entities = [WeatherResponse::class], version = 1, exportSchema = false)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}