package com.lukakordic.weatherapp

import android.app.Application
import com.lukakordic.weatherapp.di.applicationModule
import com.lukakordic.weatherapp.di.interactionModule
import com.lukakordic.weatherapp.di.networkingModule
import com.lukakordic.weatherapp.di.presentationModule
import org.koin.android.ext.android.startKoin

class WeatherApp : Application() {

    companion object {
        lateinit var instance: WeatherApp
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin(this, listOf(applicationModule, networkingModule, presentationModule, interactionModule))
    }
}
