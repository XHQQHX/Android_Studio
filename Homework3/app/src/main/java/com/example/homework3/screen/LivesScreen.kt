package com.example.homework3.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework3.viewmodel.LiveWeatherViewModel
import com.example.homework3.R
import com.example.homework3.ui.components.CitySlider
import com.example.homework3.ui.components.WeatherCard
import com.example.homework3.ui.components.WeatherPeriodItem
import com.example.homework3.ui.components.TabItem

@Composable
fun LivesScreen(onNavigateToForecasts: () -> Unit) {
    val viewModel: LiveWeatherViewModel = viewModel()
    val livesWeather = viewModel.livesWeather

    var selectedCityCode by rememberSaveable { mutableStateOf("500000") }
    LaunchedEffect(selectedCityCode) {
        Log.d("LivesScreen", "请求城市（code：$selectedCityCode）的天气")
        viewModel.loadLives(selectedCityCode) // 调用 ViewModel 加载对应城市的天气
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFB0E0E6),
        floatingActionButton = {
            // 1. 选中状态管理（记录当前激活的选项，支持屏幕旋转保存）
            var currentTab by rememberSaveable { mutableStateOf("city") } // "city" = 城市页, "forecast" = 预测页
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.45f) // 宽度占屏幕85%，适配不同设备
                    .height(60.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.12f), // 与WeatherCard背景一致
                        shape = RoundedCornerShape(26.dp) // 与WeatherCard圆角统一
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.25f), // 与现有组件边框透明度一致
                        shape = RoundedCornerShape(26.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 3. 城市选项（可点击+选中反馈+动画）
                    TabItem(
                        iconRes = R.drawable.ic_live,
                        text = "城市",
                        isSelected = currentTab == "city",
                        onClick = {
                            currentTab = "forecast"
                            // 跳转至城市页面（根据你的导航逻辑调整，示例用onNavigateToCity）
                        }
                    )
                    // 分割线（柔和风格，避免生硬）
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(36.dp)
                            .background(Color.White.copy(alpha = 0.3f))
                    )
                    // 4. 预测选项（与城市选项风格统一）
                    TabItem(
                        iconRes = R.drawable.ic_forecast,
                        text = "预测",
                        isSelected = currentTab == "forecast",
                        onClick = {
                            currentTab = "forecast"
                            onNavigateToForecasts() // 跳转至预测页面（原回调逻辑）
                        }
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center, // 底部居中
        bottomBar = { Spacer(modifier = Modifier.height(40.dp)) }// 底部边距
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().height(15.dp))
            WeatherCard(
                province = livesWeather.province,
                city = livesWeather.city,
                weather = livesWeather.weather,
                temperature = livesWeather.temperature,
                maxTemperature = "41",
                minTemperature = "9",
                windPower = livesWeather.windpower,
                windDirection = livesWeather.winddirection,
                humidity = "${livesWeather.humidity}%",
                weatherIcon = R.drawable.is_sunny
            )
            WeatherPeriodItem(
                period = "白天",
                weather = "晴",          // 多云 / 晴
                temperature = "25℃",      // 25℃
                windPower = "≤3级",        // 3级
                windDirection = "西南风",    // 西南风
                icon = R.drawable.ic_day
            )
            Spacer(modifier = Modifier.height(10.dp))
            WeatherPeriodItem(
                period = "夜晚",
                weather = "晴",          // 多云 / 晴
                temperature = "25℃",      // 25℃
                windPower = "≤3级",        // 3级
                windDirection = "西南风",    // 西南风
                icon = R.drawable.ic_nigth
            )
            Spacer(modifier = Modifier.height(30.dp))
            CitySlider(initialSelectedIndex = 0,
                onCitySelected = { cityCode ->
                    selectedCityCode = cityCode
                    Log.d("CitySlider", "选中城市：$cityCode")
                }
            )
        }
    }
}

@Preview
@Composable
fun LivesScreenPreview() {
    LivesScreen(onNavigateToForecasts = {})
}