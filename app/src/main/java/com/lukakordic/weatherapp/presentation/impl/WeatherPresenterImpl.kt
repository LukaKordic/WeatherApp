package com.lukakordic.weatherapp.presentation.impl

import com.lukakordic.weatherapp.data.response.WeatherResponse
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherPresenterImpl @Inject constructor(private val weatherInteractor: WeatherInteractor) : WeatherPresenter {

    private lateinit var view: WeatherView

    override fun setView(view: WeatherView) {
        this.view = view
    }

    override fun getWeatherData(cityName: String) = weatherInteractor.getWeatherData(cityName, getWeatherCallback())

    private fun getWeatherCallback(): Callback<WeatherResponse> = object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful) response.body()?.run { view.showWeather(this) }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            view.showNetworkError(t)
        }
    }
}