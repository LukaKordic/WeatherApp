package com.lukakordic.weatherapp.networking

import com.lukakordic.weatherapp.data.response.WeatherResponse
import com.lukakordic.weatherapp.di.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    fun getWeatherData(@Query("q") cityName: String, @Query("appid") apiKey: String = API_KEY): Call<WeatherResponse>
}
