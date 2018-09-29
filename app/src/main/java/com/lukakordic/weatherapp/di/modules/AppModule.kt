package com.lukakordic.weatherapp.di.modules

import android.content.Context
import com.lukakordic.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplicationContext(weatherApp: WeatherApp): Context = weatherApp.applicationContext
}