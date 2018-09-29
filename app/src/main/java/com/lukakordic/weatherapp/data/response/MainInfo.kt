package com.lukakordic.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class MainInfo(val temp: Double,
                    val pressure: Double,
                    val humidity: Int,
                    @SerializedName("temp_min") val tempMin: Double,
                    @SerializedName("temp_max") val tempMax: Double)