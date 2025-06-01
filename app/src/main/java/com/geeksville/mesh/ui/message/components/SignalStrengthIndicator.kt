/*
 * Copyright (c) 2025 Meshtastic LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package com.geeksville.mesh.ui.message.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SignalStrengthIndicator(
    snr: Float,
    modifier: Modifier = Modifier
) {
    // SNR ranges for signal strength levels
    val lowSignal = Float.NEGATIVE_INFINITY..0f
    val mediumSignal = 0f..5f
    val highSignal = 5f..Float.POSITIVE_INFINITY

    // Signal bars configuration
    val signalLevel = when (snr) {
        in lowSignal -> 1
        in mediumSignal -> 2
        in highSignal -> 3
        else -> 0
    }

    val barColor = when (signalLevel) {
        1 -> Color.Red
        2 -> Color(0xFFFFA500) // Orange
        3 -> Color(0xFF4CAF50) // Green
        else -> Color.Gray
    }

    Row(
        modifier = modifier.padding(horizontal = 2.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        repeat(3) { index ->
            val height = (index + 1) * 4
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(height.dp)
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawRoundRect(
                        color = if (index < signalLevel) barColor else Color.Gray.copy(alpha = 0.3f),
                        cornerRadius = CornerRadius(1f, 1f)
                    )
                }
            }
        }
    }
}
