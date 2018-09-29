package com.lukakordic.weatherapp.di

import com.lukakordic.weatherapp.WeatherApp
import com.lukakordic.weatherapp.networking.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.openweathermap.org/"

val applicationModule = module {

    single { WeatherApp.instance } //application context

    //network dependencies
    single { BASE_URL }
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single { OkHttpClient.Builder().addInterceptor(get()).build() }
    single {
        Retrofit.Builder()
                .baseUrl(get<String>())
                .client(get())
                .addConverterFactory(get())
                .build()
    }

    single { get<Retrofit>().create(WeatherApiService::class.java) }
}
