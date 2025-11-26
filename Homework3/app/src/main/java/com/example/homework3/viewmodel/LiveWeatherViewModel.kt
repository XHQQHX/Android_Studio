package com.example.homework3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.model.LivesWeather
import com.example.homework3.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LiveWeatherViewModel : ViewModel(){
    var livesWeather by mutableStateOf(LivesWeather())
        private set

    private val repository = WeatherRepository()

    fun loadLives(city: String) {
        viewModelScope.launch (){
            val response = withContext(Dispatchers.IO) {
                repository.getLiveWeather(city)
            }
            response?.lives?.firstOrNull()?.let {
                livesWeather = it
            }
        }
    }
}