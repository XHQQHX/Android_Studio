package com.example.homework3.model

data class LivesWeatherResponse (
    val lives: List<LivesWeather>
)
data class LivesWeather (
    val province: String = "",
    val city: String = "",
    val adcode: String = "",
    val weather: String = "",
    val temperature: String = "",
    val winddirection: String = "",
    val windpower: String = "",
    val humidity: String = "",
    val reporttime: String = ""
)