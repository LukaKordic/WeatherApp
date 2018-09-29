package com.lukakordic.weatherapp.di.modules

import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.interaction.impl.WeatherInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractionModule {

    @Binds
    abstract fun bindWeatherInteractor(weatherInteractorImpl: WeatherInteractorImpl): WeatherInteractor
}