package com.lukakordic.weatherapp.presentation.impl

import com.lukakordic.weatherapp.data.db.DbStorage
import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.DataHelpers
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
        weatherInteractor.getWeatherData(cityName, getWeatherCallback())
    }

    override fun fetchWeatherDataFromDb(cityName: String) = dbStorage.getCurrentWeatherForCity(cityName).run(::showData) //todo handle no result case

    private fun getWeatherCallback(): Callback<WeatherResponse> = object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful) {
                response.body()?.run {
                    dbStorage.saveCurrentWeather(this)
                    showData(this)
                    view.hideProgess()
                }
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            view.showNetworkError(t)
        }
    }

    override fun onRefreshClicked(city: String) = fetchWeatherDataFromApi(city)

    private fun showData(data: WeatherResponse) {
        showCityName(data.name)
        showWeatherIcon(data.weather[0].icon)
        showTemperature(DataHelpers.kelvinToCelsius(data.main.temp).toString())
        showMinTemperature(DataHelpers.kelvinToCelsius(data.main.tempMin).toString())
        showMaxTemperature(DataHelpers.kelvinToCelsius(data.main.tempMax).toString())
        showPressure(data.main.pressure.toString())
        showHumidity(data.main.humidity.toString())
        showDescription(data.weather[0].main)
        showDetailedDesc(data.weather[0].description)
    }

    private fun showCityName(cityName: String) = view.showCityName(cityName)

    private fun showWeatherIcon(icon: String) = view.showWeatherIcon(icon)

    private fun showTemperature(temperature: String) = view.showTemperature(temperature)

    private fun showMinTemperature(minTemperature: String) = view.showMinTemperature(minTemperature)

    private fun showMaxTemperature(maxTemperature: String) = view.showMaxTemperature(maxTemperature)

    private fun showPressure(pressure: String) = view.showPressure(pressure)

    private fun showHumidity(humidity: String) = view.showHumidity(humidity)

    private fun showDescription(desc: String) = view.showDescription(desc)

    private fun showDetailedDesc(detailedDesc: String) = view.showDetailedDesc(detailedDesc)
}
