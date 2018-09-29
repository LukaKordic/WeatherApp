package com.lukakordic.weatherapp.di.modules

import com.lukakordic.weatherapp.networking.WeatherApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "api.openweathermap.org/"
const val API_KEY = "d126a8ce844f0df4bd46c43c805d6509"


@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun okhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                client(okHttpClient)
                addConverterFactory(gsonConverterFactory)
            }.build()

    @Singleton
    @Provides
    fun weatherService(retrofit: Retrofit): WeatherApiService = retrofit.create(WeatherApiService::class.java)
}