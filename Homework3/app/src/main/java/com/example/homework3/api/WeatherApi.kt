package com.example.homework3.api

import android.util.Log
import com.example.homework3.model.LivesWeatherResponse
import com.example.homework3.model.ForecastsWeatherResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object WeatherApi {
    private const val API_KEY = "3ea5699df09480d1f8d711feb04bea6a"
    private const val BASE_URL = "https://restapi.amap.com/v3/weather"

    private val client = OkHttpClient()
    private val gson = Gson()

    val instance = this

    suspend fun getLiveWeather(city: String): LivesWeatherResponse? {
        val url = "$BASE_URL/weatherInfo?city=$city&&extensions=base&key=$API_KEY"
        return request(url)?.let { gson.fromJson(it, LivesWeatherResponse::class.java) }
    }

    suspend fun getForecastsWeather(city: String): ForecastsWeatherResponse? {
        val url = "$BASE_URL/weatherInfo?city=$city&&extensions=all&key=$API_KEY"
        return request(url)?.let { gson.fromJson(it, ForecastsWeatherResponse::class.java) }
    }

    private suspend fun request(url: String): String? {
        // 打印实际访问的URL
        Log.d("WeatherApi", "请求URL: $url")
        return try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.d("WeatherApi", "响应成功: $responseBody")
                responseBody
            } else {
                Log.d("WeatherApi", "响应失败: ${response.code}")
                null
            }
        } catch (e: Exception) {
            Log.e("WeatherApi", "请求异常", e)
            e.printStackTrace()
            null
        }
    }
}