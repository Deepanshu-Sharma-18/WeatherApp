package com.example.weatherapplication.ui.theme.network

import com.example.weatherapplication.ui.theme.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather (@Query("q")query :String,
                            @Query("appid") appid : String = "d98541bf182b291be3fc1fa565a1c44f"
                            ) : Weather
}