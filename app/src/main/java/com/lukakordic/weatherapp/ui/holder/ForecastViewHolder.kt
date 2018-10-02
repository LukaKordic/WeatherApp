package com.lukakordic.weatherapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.lukakordic.weatherapp.R
import com.lukakordic.weatherapp.data.model.Forecast
import com.lukakordic.weatherapp.utils.DataHelpers
import com.lukakordic.weatherapp.utils.constants.CITY_NAME
import com.lukakordic.weatherapp.utils.extensions.loadForecastIcon
import kotlinx.android.synthetic.main.forecast_list_item.view.*

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(forecast: Forecast) = with(itemView) {
        temperature.text = context.getString(R.string.temperature, DataHelpers.kelvinToCelsius(forecast.main.temp))
        minTemp.text = context.getString(R.string.min_temp, DataHelpers.kelvinToCelsius(forecast.main.tempMin))
        maxTemp.text = context.getString(R.string.max_temp, DataHelpers.kelvinToCelsius(forecast.main.tempMax))
        forecastIcon.loadForecastIcon(forecast.weather[0].icon)
        time.text = forecast.dt_txt
        humidity.text = context.getString(R.string.humidity, forecast.main.humidity)
        pressure.text = context.getString(R.string.pressure, forecast.main.pressure)
        description.text = context.getString(R.string.description, forecast.weather[0].description)
    }
}