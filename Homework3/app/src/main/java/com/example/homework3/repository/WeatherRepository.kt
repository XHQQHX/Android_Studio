package com.example.homework3.repository

import com.example.homework3.api.WeatherApi
import com.example.homework3.model.ForecastsWeatherResponse
import com.example.homework3.model.LivesWeatherResponse

class WeatherRepository {
    private val api = WeatherApi.instance
    suspend fun getLiveWeather(city: String): LivesWeatherResponse? {
        return api.getLiveWeather(city)
    }
    suspend fun getForecastsWeather(city: String): ForecastsWeatherResponse? {
        return api.getForecastsWeather(city)
    }

}