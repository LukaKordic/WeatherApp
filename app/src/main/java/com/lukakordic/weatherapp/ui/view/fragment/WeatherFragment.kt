package com.lukakordic.weatherapp.ui.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.loadWeatherIcon
import com.lukakordic.weatherapp.utils.toast
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment(), WeatherView {

    private val weatherPresenter: WeatherPresenter by inject()

    companion object {
        fun getInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherPresenter.setView(this)
        weatherPresenter.fetchWeatherData("Osijek")
    }

    override fun showCityName(city: String) {
        cityName.text = city
    }

    override fun showWeatherIcon(icon: String) {
        weatherIcon.loadWeatherIcon(icon)
    }

    override fun showTemperature(temp: String) {
    }

    override fun showMinTemperature(minTemp: String) {
    }

    override fun showMaxTemperature(maxTemp: String) {
    }

    override fun showPressure(pressure: String) {
    }

    override fun showHumidity(humidity: String) {
    }

    override fun showNetworkError(error: Throwable) = toast("Jebi ga, ne radi")
}
