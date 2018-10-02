package com.lukakordic.weatherapp.ui.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.utils.NetworkUtils
import com.lukakordic.weatherapp.utils.extensions.hide
import com.lukakordic.weatherapp.utils.extensions.loadWeatherIcon
import com.lukakordic.weatherapp.utils.extensions.show
import com.lukakordic.weatherapp.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment(), WeatherView {

    private val weatherPresenter: WeatherPresenter by inject()

    companion object {
        fun getInstance() = WeatherFragment() //use getInstance for fragment creation when passing arguments in Bundle
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherPresenter.setView(this)
        activity?.refresh?.setOnClickListener { refreshData() }

        if (NetworkUtils.hasNetworkAccess(activity!!)) weatherPresenter.fetchWeatherDataFromApi("Osijek")
        else weatherPresenter.fetchWeatherDataFromDb("Osijek")
    }

    private fun refreshData() {
        if (NetworkUtils.hasNetworkAccess(activity!!)) weatherPresenter.onRefreshClicked("Osijek")
        else toast(getString(R.string.no_internet))
    }

    override fun showCityName(city: String) {
        cityName.text = city
    }

    override fun showWeatherIcon(icon: String) {
        weatherIcon.loadWeatherIcon(icon)
    }

    override fun showTemperature(temp: Double) {
        temperature.text = getString(R.string.temperature, temp)
    }

    override fun showMinTemperature(minTemp: Double) {
        minTemperature.text = getString(R.string.min_temp, minTemp)
    }

    override fun showMaxTemperature(maxTemp: Double) {
        maxTemperature.text = getString(R.string.max_temp, maxTemp)
    }

    override fun showPressure(pressure: Double) {
        pressureValue.text = getString(R.string.temperature, pressure)
    }

    override fun showHumidity(humidity: Int) {
        humidityValue.text = getString(R.string.humidity, humidity)
    }

    override fun showDescription(desc: String) {
        description.text = getString(R.string.description, desc)
    }

    override fun showDetailedDesc(detailedDesc: String) {
        detailedDescription.text = getString(R.string.detailed_description, detailedDesc)
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgess() {
        progressBar.hide()
    }

    override fun showNetworkError(error: Throwable) = toast("Jebi ga, ne radi")
}
