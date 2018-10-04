package com.lukakordic.weatherapp.di

import android.arch.persistence.room.Room
import com.lukakordic.weatherapp.WeatherApp
import com.lukakordic.weatherapp.data.db.DbStorage
import com.lukakordic.weatherapp.data.db.DbStorageImpl
import com.lukakordic.weatherapp.data.db.WeatherDb
import com.lukakordic.weatherapp.interaction.ForecastInteractor
import com.lukakordic.weatherapp.interaction.WeatherInteractor
import com.lukakordic.weatherapp.interaction.impl.ForecastInteractorImpl
import com.lukakordic.weatherapp.interaction.impl.WeatherInteractorImpl
import com.lukakordic.weatherapp.networking.WeatherApiService
import com.lukakordic.weatherapp.presentation.ForecastPresenter
import com.lukakordic.weatherapp.presentation.WeatherPresenter
import com.lukakordic.weatherapp.presentation.impl.ForecastPresenterImpl
import com.lukakordic.weatherapp.presentation.impl.WeatherPresenterImpl
import com.lukakordic.weatherapp.utils.constants.DB_NAME
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.openweathermap.org/"

val applicationModule = module {
    single { WeatherApp.instance } //application context
}

val networkingModule = module {
    single { BASE_URL }
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }
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

val presentationModule = module {
    factory { WeatherPresenterImpl(get(), get()) as WeatherPresenter }
    factory { ForecastPresenterImpl(get(), get()) as ForecastPresenter }
}

val interactionModule = module {
    factory { WeatherInteractorImpl(get()) as WeatherInteractor }
    factory { ForecastInteractorImpl(get()) as ForecastInteractor }
}

val dbModule = module {
    single {
        Room.databaseBuilder(get(), WeatherDb::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
    }
    single { get<WeatherDb>().weatherDao() }
    single { get<WeatherDb>().forecastDao() }
    single { DbStorageImpl(get(), get()) as DbStorage }
}
