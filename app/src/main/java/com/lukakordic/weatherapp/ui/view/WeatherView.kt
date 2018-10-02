package com.lukakordic.weatherapp.ui.view

interface WeatherView {

    fun showCityName(city: String)

    fun showWeatherIcon(icon: String)

    fun showTemperature(temp: Double)

    fun showMinTemperature(minTemp: Double)

    fun showMaxTemperature(maxTemp: Double)

    fun showPressure(pressure: Double)

    fun showHumidity(humidity: Int)

    fun showDescription(desc: String)

    fun showDetailedDesc(detailedDesc: String)

    fun showNetworkError(error: Throwable)

    fun showProgress()

    fun hideProgess()

    fun showNoInternetToast()
}
