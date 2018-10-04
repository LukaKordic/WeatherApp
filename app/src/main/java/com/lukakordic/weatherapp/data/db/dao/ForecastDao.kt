package com.lukakordic.weatherapp.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.utils.constants.FORECAST_TABLE

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeForecastResponse(responseToStore: ForecastResponse)

    @Query("SELECT * FROM $FORECAST_TABLE WHERE name = :city LIMIT 1")
    fun getForecastForCity(city: String): ForecastResponse
}