package com.lukakordic.weatherapp.presentation.impl

import com.lukakordic.weatherapp.data.db.DbStorage
import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.DataHelpers
import com.lukakordic.weatherapp.utils.constants.CITY_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenterImpl constructor(private val weatherInteractor: WeatherInteractor,
                                       private val dbStorage: DbStorage) : WeatherPresenter {

    private lateinit var view: WeatherView

    override fun setView(view: WeatherView) {
        this.view = view
    }

    override fun fetchWeatherDataFromApi(cityName: String) {
        view.showProgress()
        val city = if (cityName.isNotBlank()) cityName else CITY_NAME
        weatherInteractor.getWeatherData(city, getWeatherCallback())
    }

    override fun fetchWeatherDataFromDb(cityName: String) {
        val data = dbStorage.getCurrentWeatherForCity(cityName)
        if (data != null) showData(data) // this can be null if there is nothing in db
        else view.showDbError()
        view.hideProgess()
    }

    private fun getWeatherCallback(): Callback<WeatherResponse> = object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful) {
                response.body()?.run {
                    dbStorage.saveCurrentWeatherData(this)
                    showData(this)
                    view.hideProgess()
                }
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            view.showNetworkError(t)
        }
    }

    private fun showData(data: WeatherResponse) {
        showCityName(data.name)
        showWeatherIcon(data.weather[0].icon)
        showTemperature(DataHelpers.kelvinToCelsius(data.main.temp))
        showMinTemperature(DataHelpers.kelvinToCelsius(data.main.tempMin))
        showMaxTemperature(DataHelpers.kelvinToCelsius(data.main.tempMax))
        showPressure(data.main.pressure)
        showHumidity(data.main.humidity)
        showDescription(data.weather[0].main)
        showDetailedDesc(data.weather[0].description)
    }

    private fun showCityName(cityName: String) = view.showCityName(cityName)

    private fun showWeatherIcon(icon: String) = view.showWeatherIcon(icon)

    private fun showTemperature(temperature: Double) = view.showTemperature(temperature)

    private fun showMinTemperature(minTemperature: Double) = view.showMinTemperature(minTemperature)

    private fun showMaxTemperature(maxTemperature: Double) = view.showMaxTemperature(maxTemperature)

    private fun showPressure(pressure: Double) = view.showPressure(pressure)

    private fun showHumidity(humidity: Int) = view.showHumidity(humidity)

    private fun showDescription(desc: String) = view.showDescription(desc)

    private fun showDetailedDesc(detailedDesc: String) = view.showDetailedDesc(detailedDesc)
}
