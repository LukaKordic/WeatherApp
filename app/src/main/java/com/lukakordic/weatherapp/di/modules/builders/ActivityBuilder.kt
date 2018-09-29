package com.lukakordic.weatherapp.di.modules.builders

import com.lukakordic.weatherapp.ui.view.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindWeatherActivity(): HomeActivity
}