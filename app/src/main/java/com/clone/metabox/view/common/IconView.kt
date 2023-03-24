package com.clone.metabox.view.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun IconView (
    painter: Painter,
    description: String,
    tint: Color = Color.White,
    size: Int = 28
) {
    Icon(
        painter = painter,
        contentDescription = description,
        tint = tint,
        modifier = Modifier
            .size(size.dp)
    )
}