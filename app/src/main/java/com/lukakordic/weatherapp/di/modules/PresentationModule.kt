package com.lukakordic.weatherapp.di.modules

import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.presentation.impl.WeatherPresenterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PresentationModule {

    @Singleton
    @Binds
    abstract fun bindWeatherPresenter(weatherPresenterImpl: WeatherPresenterImpl): WeatherPresenter
}