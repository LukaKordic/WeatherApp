package com.lukakordic.weatherapp.ui.view.fragment

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.location.places.ui.PlacePicker
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.ui.view.PassCityInterface
import com.lukakordic.weatherapp.ui.view.WeatherView
import com.lukakordic.weatherapp.ui.view.activity.HomeActivity
import com.lukakordic.weatherapp.utils.DataHelpers
import com.lukakordic.weatherapp.utils.DialogHelper
import com.lukakordic.weatherapp.utils.NetworkUtils
import com.lukakordic.weatherapp.utils.constants.ACCESS_LOCATION_PERMISSION_CODE
import com.lukakordic.weatherapp.utils.constants.CITY_NAME
import com.lukakordic.weatherapp.utils.extensions.hide
import com.lukakordic.weatherapp.utils.extensions.loadWeatherIcon
import com.lukakordic.weatherapp.utils.extensions.show
import com.lukakordic.weatherapp.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class WeatherFragment : Fragment(), WeatherView, ActivityCompat.OnRequestPermissionsResultCallback {

    private val weatherPresenter: WeatherPresenter by inject()
    private val PLACE_AUTOCOMPLETE_RC = 1
    private lateinit var city: String
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var passCityInterface: PassCityInterface
    private val PLACE_MAPS = 2

    companion object {
        fun getInstance() = WeatherFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        passCityInterface = activity as HomeActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherPresenter.setView(this)

        getWeatherForCurrentLocation()

        searchCity.setOnClickListener {
            DialogHelper.showPickerDialog(activity!!,
                    { createSearchIntent() }, { createPickPlaceIntent() })
        }

        activity?.refresh?.setOnClickListener {
            getWeatherForCurrentLocation()
        }
    }

    private fun getWeatherForCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity?.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    city = if (it != null) {
                        DataHelpers.getAddress(it.longitude, it.latitude, activity!!)
                    } else {
                        CITY_NAME
                    }
                    getWeatherData(city)
                }
            } else { //request permission if not granted
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), ACCESS_LOCATION_PERMISSION_CODE)
            }
        } else { //if version < M
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
            fusedLocationClient.lastLocation.addOnSuccessListener {
                city = if (it != null) {
                    DataHelpers.getAddress(it.longitude, it.latitude, activity!!)
                } else {
                    CITY_NAME
                }
                getWeatherData(city)
            }
        }
    }

    private fun getWeatherData(city: String) {
        if (hasConnection()) weatherPresenter.fetchWeatherDataFromApi(city)
        else weatherPresenter.fetchWeatherDataFromDb(city)
        passCityInterface.passCity(city)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == ACCESS_LOCATION_PERMISSION_CODE) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED))
                getWeatherForCurrentLocation()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PLACE_AUTOCOMPLETE_RC -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val place = PlaceAutocomplete.getPlace(activity, data)
                        city = place.name.toString()
                        getWeatherData(city)
                    }
                    PlaceAutocomplete.RESULT_ERROR -> {
                        val status = PlaceAutocomplete.getStatus(activity, data)
                        Log.i("TAG", status.statusMessage)
                    }
                    RESULT_CANCELED -> {
                        // canceled
                    }
                }
            }
            PLACE_MAPS -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val place = PlacePicker.getPlace(activity, data)
                        val parts = place.address.toString().split(",")
                        if (parts.size >= 3)
                            getWeatherData(parts[2])
                        else getWeatherData(parts[0])
                    }
                    RESULT_CANCELED -> {

                    }
                }
            }
        }
    }

    private fun hasConnection(): Boolean = NetworkUtils.hasNetworkAccess(activity!!)

    private fun createSearchIntent() {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity)
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_RC)
        } catch (error: GooglePlayServicesNotAvailableException) {
            toast(getString(R.string.services_unavailable))
        }
    }

    private fun createPickPlaceIntent() {
        val intent = PlacePicker.IntentBuilder().build(activity)
        startActivityForResult(intent, PLACE_MAPS)
    }

    override fun showCityName(city: String) {
        cityName.text = city
    }

    override fun showWeatherIcon(icon: String) {
        forecastIcon.loadWeatherIcon(icon)
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

    override fun showProgress() = progressBar.show()

    override fun hideProgess() = progressBar.hide()

    override fun showDbError() = toast(getString(R.string.empty_db))

    override fun showNetworkError(error: Throwable) = toast(error.message
            ?: getString(R.string.network_error))
}
