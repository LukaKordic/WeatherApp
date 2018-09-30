package com.lukakordic.weatherapp.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.lukakordic.weatherapp.utils.WEATHER_TABLE

@Entity(tableName = WEATHER_TABLE)
data class WeatherResponse(
        @PrimaryKey
        val id: Int,
        val weather: List<Weather>,
        @Embedded
        val main: MainInfo,
        @Embedded
        val wind: Wind,
        val name: String
)