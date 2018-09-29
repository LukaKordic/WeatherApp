package com.lukakordic.weatherapp.data.response

data class Weather(val id: Int,
                   val main: String,
                   val description: String,
                   val icon: String)