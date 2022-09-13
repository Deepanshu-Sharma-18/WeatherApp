package com.example.weatherapplication.ui.theme.di

import com.example.weatherapplication.ui.theme.network.CityApi
import com.example.weatherapplication.ui.theme.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCity() : CityApi{
        return Retrofit.Builder().baseUrl("https://autocomplete-india.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(CityApi::class.java)
    }


}