package com.lukakordic.weatherapp.di.components

import com.lukakordic.weatherapp.WeatherApp
import com.lukakordic.weatherapp.di.modules.AppModule
import com.lukakordic.weatherapp.di.modules.builders.ActivityBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<WeatherApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApp>()
}