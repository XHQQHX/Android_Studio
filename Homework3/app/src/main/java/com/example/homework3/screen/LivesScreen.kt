package com.example.homework3.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework3.viewmodel.LiveWeatherViewModel

@Composable
fun LivesScreen(onNavigateToForecasts: () -> Unit) {
    val viewModel: LiveWeatherViewModel = viewModel()
    val livesWeather = viewModel.livesWeather
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
                onClick = { viewModel.loadLives(inputCity) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("查询实时天气")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("城市：${livesWeather.city}")
            Text("天气：${livesWeather.weather}")
            Text("气温：${livesWeather.temperature} ℃")
            Text("风向：${livesWeather.winddirection}")
            Text("风力：${livesWeather.windpower}")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNavigateToForecasts,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("查看天气预报 →")
            }
        }
    }
}