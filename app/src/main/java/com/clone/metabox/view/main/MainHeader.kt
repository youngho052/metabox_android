package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clone.metabox.view.common.IconView
import com.clone.metabox.R

@Composable
fun MainHeader (
    contentTitle: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xFF120d3c))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("$contentTitle",color = Color.White)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Text(text = "ticket",color = Color.White)
            IconView(
                painter = painterResource(id = R.drawable.icon_menu_white),
                description = "${R.drawable.icon_menu_white}",
                size = 30
            )
        }
    }
}