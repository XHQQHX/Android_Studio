package com.example.homework3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    province: String,
    city: String,
    weather: String,
    temperature: String,
    maxTemperature: String,
    minTemperature: String,
    windPower: String,
    windDirection: String,
    humidity: String,
    weatherIcon: Int
) {
    Box(
        modifier = modifier
            .width(360.dp)
            .height(400.dp)
            .background(
                Color.White.copy(alpha = 0.12f),   // 更轻的透明
                shape = RoundedCornerShape(26.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(26.dp)
            )
            .padding(18.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 顶部：城市 + 天气
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$province · $city",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = weather,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // 图标 + 温度
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    painter = painterResource(id = weatherIcon),
                    contentDescription = weather,
                    modifier = Modifier.size(110.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${temperature}℃",
                    color = Color.White,
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "↑${maxTemperature}℃   ↓${minTemperature}℃",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 18.sp
                )
            }

            // 底部信息小卡片
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherInfoChip(title = "风力", value = windPower)
                WeatherInfoChip(title = "风向", value = windDirection)
                WeatherInfoChip(title = "湿度", value = humidity)
            }
        }
    }
}

@Composable
fun WeatherInfoChip(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Box(
        modifier = modifier
            .width(90.dp)
            .height(70.dp)
            .background(
                Color.White.copy(alpha = 0.18f),
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title\n$value",
            color = Color.White,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
