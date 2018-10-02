package com.lukakordic.weatherapp.ui.view

import com.lukakordic.weatherapp.data.model.ForecastResponse

interface ForecastView {

    fun showProgress()

    fun hideProgress()

    fun showNoInternetToast()

    fun showNetworkError(error: Throwable)

    fun showData(data: ForecastResponse)
}