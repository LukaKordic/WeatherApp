package com.lukakordic.weatherapp.presentation.impl

import com.lukakordic.weatherapp.data.db.DbStorage
import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenterImpl constructor(private val weatherInteractor: WeatherInteractor,
                                       private val dbStorage: DbStorage) : WeatherPresenter {

    private lateinit var view: WeatherView

    override fun setView(view: WeatherView) {
        this.view = view
    }

    override fun fetchWeatherData(cityName: String) = weatherInteractor.getWeatherData(cityName, getWeatherCallback())

    private fun getWeatherCallback(): Callback<WeatherResponse> = object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful) {
                response.body()?.run {
                    dbStorage.saveCurrentWeather(this)
                    showData(this)
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
    }

    private fun showCityName(cityName: String) = view.showCityName(cityName)
    private fun showWeatherIcon(icon: String) = view.showWeatherIcon(icon)
}
