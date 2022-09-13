package com.example.weatherapplication.ui.theme.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.ui.theme.di.DataOrException
import com.example.weatherapplication.ui.theme.modelAutoComplete.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor( private val repository: SearchRepository) : ViewModel(){
    suspend fun getCityName(city : String) : DataOrException<Search,Boolean,Exception>{
        return repository.getCity(cityname = city)
    }
}