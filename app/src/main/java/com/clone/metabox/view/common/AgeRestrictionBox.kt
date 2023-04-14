package com.clone.metabox.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AgeRestrictionBox(
    age: String,
) {
    Row(
        verticalAlignment= Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .let {
                when(age) {
                    "12" -> Modifier.background(Color(0xFFE9B630), RoundedCornerShape(3.dp))
                    "15" -> Modifier.background(Color(0xFFDD752F), RoundedCornerShape(3.dp))
                    "19" -> Modifier.background(Color(0xFFD92B36), RoundedCornerShape(3.dp))
                    else -> Modifier
                }
            }
            .size(20.dp)
    ) {
        Text(
            text = "$age",
            color = Color.White,
            fontSize = 15.sp
        )
    }
}

