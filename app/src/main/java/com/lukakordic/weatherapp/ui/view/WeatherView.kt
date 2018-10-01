package com.lukakordic.weatherapp.ui.view

interface WeatherView {

    fun showCityName(city: String)

    fun showWeatherIcon(icon: String)

    fun showTemperature(temp: String)

    fun showMinTemperature(minTemp: String)

    fun showMaxTemperature(maxTemp: String)

    fun showPressure(pressure: String)

    fun showHumidity(humidity: String)

    fun showDescription(desc: String)

    fun showDetailedDesc(detailedDesc: String)

    fun showNetworkError(error: Throwable)

    fun showProgress()

    fun hideProgess()
}
