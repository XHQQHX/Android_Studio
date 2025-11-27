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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherPeriodItem(
    period: String,          // 标题（如 白天 / 夜晚）
    icon: Int,              // 天气图标
    weather: String,        // 天气描述
    temperature: String,    // 温度
    windPower: String,      // 风力
    windDirection: String   // 风向
) {
    Box(
        modifier = Modifier
            .offset(y = 10.dp)
            .size(360.dp, 80.dp)
            .background(
                Color.White.copy(alpha = 0.12f),   // 更轻的透明
                shape = RoundedCornerShape(26.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(26.dp)
            )
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            // ---------- 第一行：标题 + 字段标签 ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = period,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    modifier = Modifier.weight(4f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("天气", color = Color.White, fontSize = 14.sp)
                    Text("温度", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(x = (-8).dp))
                    Text("风力", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(x = (-10).dp))
                    Text("风向", color = Color.White, fontSize = 14.sp, modifier = Modifier.offset(x = (-10).dp))
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // ---------- 第二行：图标 + 信息 ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "weather_icon",
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)
                        .padding(end = 25.dp),
                    tint = Color.White
                )

                Row(
                    modifier = Modifier.weight(4f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(weather, color = Color.White, fontSize = 16.sp)
                    Text(temperature, color = Color.White, fontSize = 16.sp, lineHeight = 28.sp)
                    Text(windPower, color = Color.White, fontSize = 16.sp)
                    Text(windDirection, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}