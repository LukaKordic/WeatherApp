package com.lukakordic.weatherapp.data.model

import android.arch.persistence.room.Entity

//@Entity
data class Wind(val speed: Double,
                val deg: Int
)