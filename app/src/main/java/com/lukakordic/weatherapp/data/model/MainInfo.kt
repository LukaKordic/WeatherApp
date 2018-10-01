package com.lukakordic.weatherapp.data.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

//@Entity
data class MainInfo(val temp: Double,
                    val pressure: Double,
                    val humidity: Int,
                    @SerializedName("temp_min") val tempMin: Double,
                    @SerializedName("temp_max") val tempMax: Double)