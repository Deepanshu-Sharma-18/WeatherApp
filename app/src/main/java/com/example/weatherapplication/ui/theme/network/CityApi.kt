package com.example.weatherapplication.ui.theme.network

import com.example.weatherapplication.ui.theme.modelAutoComplete.Search
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CityApi {
    @Headers("X-RapidAPI-Key: 043d809953mshc200f413ae50ed9p13c424jsn52fab6f5975b",
    "X-RapidAPI-Host: autocomplete-india.p.rapidapi.com")
    @GET("marketplace/autocomplete/india/cities/{city}")
    suspend fun getCity(
        @Path("city") text :String
    ) : Search
}