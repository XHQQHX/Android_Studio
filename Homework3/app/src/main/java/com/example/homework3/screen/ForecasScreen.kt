package com.example.homework3.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework3.viewmodel.ForecastWeatherViewModel

@Composable
fun ForecastsScreen(onNavigateToLives: () -> Unit) {
    val viewModel: ForecastWeatherViewModel = viewModel()
    val forecastWeather = viewModel.forecastsWeather

    var inputCity by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = inputCity,
                onValueChange = { inputCity = it },
                label = { Text("输入城市代码（如 110000）") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.loadForecasts(inputCity) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("查询天气预报")
            }

            Spacer(modifier = Modifier.height(32.dp))

            forecastWeather?.casts?.forEach { cast ->
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("日期：${cast.date}")
                    Text("白天天气：${cast.dayweather}, 温度：${cast.daytemp}℃")
                    Text("夜间天气：${cast.nightweather}, 温度：${cast.nighttemp}℃")
                }
                Divider()
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNavigateToLives,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← 返回实时天气")
            }
        }
    }
}