package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.view.common.IconView
import com.clone.metabox.R

@Composable
fun MainFooter (
    navigateTheaterInfo: () -> Unit,
    navigateMovieList: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.White)
                .border(1.dp, Color(0xFFE9E9E9))
        ) {
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_home_black),
                category = "홈",
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_movie_black),
                category = "영화",
                modifier = Modifier.clickable {
                    navigateMovieList()
                }
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_theater_black),
                category = "극장",
                modifier = Modifier.clickable {
                    navigateTheaterInfo()
                }
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_calendar_black),
                category = "예매"
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_profile_black),
                category = "마이페이지"
            )
        }
    }
}

@Composable
fun IconBox (
    imagePainter: Painter,
    category: String,
    tint: Color = Color.Unspecified,
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
            color = Color.Black,
            fontSize = 11.sp
        )
    }
}