package com.lukakordic.weatherapp.data.model

data class Forecast(val dt_txt: String,
                    val main: MainForecast,
                    val weather: List<Weather>)