package com.lukakordic.weatherapp.data.response

data class WeatherResponse(
        val weather: List<Weather>,
        val main: MainInfo,
        val wind: Wind,
        val clouds: Cloud
)