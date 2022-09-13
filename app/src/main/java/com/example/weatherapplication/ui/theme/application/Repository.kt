package com.example.weatherapplication.ui.theme.application

import android.util.Log
import com.example.weatherapplication.ui.theme.di.DataOrException
import com.example.weatherapplication.ui.theme.models.Weather
import com.example.weatherapplication.ui.theme.network.WeatherApi
import javax.inject.Inject

class Repository @Inject constructor(val weatherApi: WeatherApi) {

    suspend fun getWeather(name :String) : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            weatherApi.getWeather(query = name)

        }catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return  DataOrException(data = response)
    }
}