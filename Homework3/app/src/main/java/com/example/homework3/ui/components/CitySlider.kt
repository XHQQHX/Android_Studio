package com.example.homework3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CityInfo(
    val name: String,
    val code: String // 区域编号
)
@Composable
fun CitySlider(
    modifier: Modifier = Modifier,
    initialSelectedIndex: Int = 0, // 初始选中索引
    onCitySelected: (String) -> Unit // 城市选中回调
) {
    // 城市列表（固定为重庆、北京、上海、浙江）
    val cities = listOf(
        CityInfo("重庆", "500000"), // 重庆市区域编号
        CityInfo("北京", "110000"), // 北京市区域编号
        CityInfo("上海", "310000"), // 上海市区域编号
        CityInfo("杭州", "330100")  // 杭州市区域编号
    )
    // 选中状态管理（支持屏幕旋转保存）
    var selectedIndex by rememberSaveable { mutableIntStateOf(initialSelectedIndex) }

    Box(
        modifier = modifier
            .width(300.dp)
            .height(60.dp)
            .background(
                Color.White.copy(alpha = 0.12f),
                shape = RoundedCornerShape(26.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(26.dp)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            cities.forEachIndexed { index, cityInfo ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            color = if (index == selectedIndex) {
                                Color.White.copy(alpha = 0.25f)
                            } else {
                                Color.Transparent
                            },
                            shape = RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            selectedIndex = index
                            onCitySelected(cityInfo.code) // 回调选中的城市区域信息
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cityInfo.name,
                        color = if (index == selectedIndex) {
                            Color.White
                        } else {
                            Color.White.copy(alpha = 0.8f)
                        },
                        fontSize = 18.sp,
                        fontWeight = if (index == selectedIndex) {
                            FontWeight.Bold
                        } else {
                            FontWeight.SemiBold
                        }
                    )
                }
            }
        }
    }
}