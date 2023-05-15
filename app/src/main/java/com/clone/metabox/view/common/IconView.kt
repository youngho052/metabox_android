package com.clone.metabox.view.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconView (
    painter: Painter,
    description: String,
    tint: Color = Color.White,
    size: Int = 28,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painter,
        contentDescription = description,
        tint = tint,
        modifier = modifier
            .size(size.dp)
    )
}

@Composable
fun IconTextView (
    imagePainter: Painter,
    category: String,
    tint: Color = Color.Unspecified,
    fontColor: Color = Color.Black,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        IconView(
            painter = imagePainter,
            description = "$imagePainter",
            tint = tint,
            size = 30
        )
        Text(
            text = "$category",
            color = fontColor,
            fontSize = 11.sp
        )
    }
}