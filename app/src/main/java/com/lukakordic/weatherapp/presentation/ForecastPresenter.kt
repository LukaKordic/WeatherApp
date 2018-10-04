package com.lukakordic.weatherapp.presentation

import com.lukakordic.weatherapp.ui.view.ForecastView

interface ForecastPresenter {

    fun setView(view: ForecastView)

    fun fetchForecastFromApi(cityName: String)

    fun fetchForecastFromDb(cityName: String)

    fun onRefreshClicked(city: String)


}