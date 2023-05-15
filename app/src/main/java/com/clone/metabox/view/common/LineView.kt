package com.clone.metabox.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLineView (
    color: Color,
    height: Int = 1
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height.dp)
        .background(color)
    )
}

@Composable
fun VerticalLineView(
    color: Color,
    width: Int = 1,
) {
    Box(modifier = Modifier
        .width(width.dp)
        .fillMaxHeight()
        .background(color)
    )
}