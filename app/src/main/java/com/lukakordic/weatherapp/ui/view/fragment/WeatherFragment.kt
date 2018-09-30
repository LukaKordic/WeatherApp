package com.lukakordic.weatherapp.ui.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.data.response.WeatherResponse
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.toast
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment(), WeatherView {

    private val weatherPresenter: WeatherPresenter by inject()

    override fun showWeather(weather: WeatherResponse) = toast("Incoming")

    override fun showNetworkError(error: Throwable) = toast("Jebi ga, ne radi")

    companion object {
        fun getInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherPresenter.setView(this)
        weatherPresenter.getWeatherData("Osijek")
    }
}
