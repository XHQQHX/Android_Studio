package com.example.homework3.model

data class ForecastsWeatherResponse (
    val forecasts: List<ForecastsWeather>
)
data class ForecastsWeather (
    val city: String = "",
    val adcode: String = "",
    val province: String = "",
    val reporttime: String = "",
    val casts: List<Casts>
)
data class Casts (
    val date: String = "",
    val week: String = "",
    val dayweather: String = "",
    val nightweather: String = "",
    val daytemp: String = "",
    val nighttemp: String = "",
    val daywind: String = "",
    val nightwind: String = "",
    val daypower: String = "",
    val nightpower: String = ""
)