package com.example.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashedBorder(
    strokeWidth: Dp = 1.5.dp,
    color: Color = Color(0xFFBDBDBD),
    cornerRadius: Dp = 12.dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 6.dp
): Modifier = this.drawBehind {
    val stroke = Stroke(
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            floatArrayOf(dashLength.toPx(), gapLength.toPx()),
            0f
        )
    )
    drawRoundRect(
        color = color,
        cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
        style = stroke
    )
}
