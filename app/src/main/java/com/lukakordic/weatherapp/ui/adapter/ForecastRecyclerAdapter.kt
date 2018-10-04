package com.lukakordic.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.data.model.Forecast
import com.lukakordic.weatherapp.data.model.ForecastResponse
import com.lukakordic.weatherapp.ui.holder.ForecastViewHolder

class ForecastRecyclerAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    private val forecastList = arrayListOf<Forecast>()
    private lateinit var city: String

    fun updateData(data: ForecastResponse) {
        forecastList.clear()
        forecastList.addAll(data.list)
        notifyDataSetChanged()
    }

    fun setCity(city: String) {
        this.city = city
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_list_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position], city)
    }

    override fun getItemCount(): Int = forecastList.size
}