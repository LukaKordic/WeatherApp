package com.lukakordic.weatherapp.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.lukakordic.weatherapp.utils.constants.FORECAST_TABLE

@Entity(tableName = FORECAST_TABLE)
data class ForecastResponse(
        @PrimaryKey(autoGenerate = true)
        val forecastId: Int,
        @Embedded
        val city: City,
        val list: List<Forecast>)