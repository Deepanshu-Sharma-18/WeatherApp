package com.example.weatherapplication.ui.theme.screens.search

import android.util.Log
import com.example.weatherapplication.ui.theme.di.DataOrException
import com.example.weatherapplication.ui.theme.modelAutoComplete.Search
import com.example.weatherapplication.ui.theme.network.CityApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class SearchRepository @Inject constructor(private val cityApi: CityApi) {
    suspend fun getCity(cityname :String) : DataOrException<Search, Boolean, Exception> {
        val response = try {
           cityApi.getCity(text = cityname)

        }catch (e: Exception){
            return DataOrException(e = e)
        }
        Log.d("TAGCITYAPI", response.toString())
        return  DataOrException(data = response)
    }
}


