package com.example.homework3.ui.components

// 1. 动画相关（缩放+颜色过渡）
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.VectorConverter

// 2. 布局与交互相关
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

// 3. Material3 UI 组件
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

// 4. 状态与保存
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

// 5. 基础 UI 工具
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabItem(
    iconRes: Int,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // 选中状态动画：缩放+背景过渡
    val scale by animateValueAsState(
        targetValue = if (isSelected) 1.03f else 1.0f,
        typeConverter = Float.VectorConverter,
        label = "tabScale",
        animationSpec = tween(durationMillis = 200)
    )
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) Color.White.copy(alpha = 0.25f) else Color.Transparent,
        label = "tabBg",
        animationSpec = tween(durationMillis = 200)
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = bgColor, shape = RoundedCornerShape(18.dp))
            .scale(scale)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .semantics { role = Role.Button },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(26.dp),
                tint = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f)
            )
        }
    }
}
