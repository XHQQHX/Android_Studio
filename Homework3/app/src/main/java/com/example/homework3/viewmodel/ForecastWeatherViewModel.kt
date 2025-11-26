package com.example.homework3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.model.ForecastsWeather
import com.example.homework3.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastWeatherViewModel : ViewModel() {
    var forecastsWeather by mutableStateOf<ForecastsWeather?>(null)
        private set

    private val repository = WeatherRepository()
    fun loadForecasts(city: String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getForecastsWeather(city)
            }
            response?.forecasts?.firstOrNull()?.let {
                forecastsWeather = it
            }
        }
    }
}