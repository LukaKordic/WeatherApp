package com.lukakordic.weatherapp.ui.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.data.model.Forecast
import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.presentation.ForecastPresenter
import com.lukakordic.weatherapp.ui.adapter.ForecastRecyclerAdapter
import com.lukakordic.weatherapp.ui.view.ForecastView
import com.lukakordic.weatherapp.utils.NetworkUtils
import com.lukakordic.weatherapp.utils.constants.CITY_NAME
import com.lukakordic.weatherapp.utils.extensions.hide
import com.lukakordic.weatherapp.utils.extensions.show
import com.lukakordic.weatherapp.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class ForecastFragment : Fragment(), ForecastView {

    private val forecastPresenter: ForecastPresenter by inject()
    private val forecastAdapter = ForecastRecyclerAdapter()

    companion object {
        fun getInstance() = ForecastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forecastPresenter.setView(this)
        activity?.refresh?.setOnClickListener { refreshData(CITY_NAME) }
        activity?.toolbarTitle?.text = "Osijek"

        if (NetworkUtils.hasNetworkAccess(activity!!)) forecastPresenter.fetchForecastFromApi(CITY_NAME)
        else forecastPresenter.fetchForecastFromDb(CITY_NAME)
    }

    private fun refreshData(city: String) {
        if (NetworkUtils.hasNetworkAccess(activity!!)) forecastPresenter.fetchForecastFromApi(city)
        else toast(getString(R.string.no_internet))
    }

    private fun initRecycler() {
        with(forecastRv) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = forecastAdapter
        }
    }

    override fun showData(data: ForecastResponse) {
        forecastAdapter.updateData(data)
        initRecycler()
    }

    override fun showProgress() = forecastProgress.show()

    override fun hideProgress() = forecastProgress.hide()

    override fun showNoInternetToast() = toast(getString(R.string.no_internet))

    override fun showNetworkError(error: Throwable) = toast(error.message
            ?: getString(R.string.network_error))
}