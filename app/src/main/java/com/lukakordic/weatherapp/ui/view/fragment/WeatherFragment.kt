package com.lukakordic.weatherapp.ui.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.data.model.WeatherResponse
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.loadWeatherIcon
import com.lukakordic.weatherapp.utils.toast
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment(), WeatherView {
    override fun showCityName(city: String) {}

    override fun showWeatherIcon(icon: String) {}

    private val weatherPresenter: WeatherPresenter by inject()

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
