package com.lukakordic.weatherapp.presentation.impl

import com.lukakordic.weatherapp.data.db.DbStorage
import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.interaction.ForecastInteractor
import com.lukakordic.weatherapp.presentation.ForecastPresenter
import com.lukakordic.weatherapp.ui.view.ForecastView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastPresenterImpl constructor(private val forecastInteractor: ForecastInteractor,
                                        private val dbStorage: DbStorage) : ForecastPresenter {

    private lateinit var view: ForecastView

    override fun setView(view: ForecastView) {
        this.view = view
    }

    override fun fetchForecastFromApi(cityName: String) {
        view.showProgress()
        forecastInteractor.getForecastData(cityName, getForecastCallback())
    }

    override fun fetchForecastFromDb(cityName: String) {
        val data = dbStorage.getForecastDataForCity(cityName)
        if (data != null) showData(data) // this can be null if there is nothing in db
        else view.showNoInternetToast()
        view.hideProgress()
    }

    override fun onRefreshClicked(city: String) = fetchForecastFromApi(city)

    private fun getForecastCallback(): Callback<ForecastResponse> = object : Callback<ForecastResponse> {
        override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
            if (response.isSuccessful) {
                response.body()?.run {
                    dbStorage.saveCurrentForecastData(this)
                    showData(this)
                    view.hideProgress()
                }
            }
        }

        override fun onFailure(call: Call<ForecastResponse>, t: Throwable) = view.showNetworkError(t)
    }

    private fun showData(data: ForecastResponse) {
        view.showData(data)
    }
}