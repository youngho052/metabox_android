package com.clone.metabox.view.theaterselect

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.view.common.IconView
import com.clone.metabox.view.movielist.onClick

@Composable
fun TheaterSelectHeader () {
    val context = LocalContext.current as ComponentActivity

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.White)
            .padding(start = 18.dp, end = 18.dp)
    ) {
        IconView(
            painter = painterResource(id = R.drawable.icon_close_black),
            description = "${R.drawable.icon_close_black}",
            tint = Color.Unspecified,
            size = 30,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .onClick {
                    context.finish()
                }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "극장선택",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}