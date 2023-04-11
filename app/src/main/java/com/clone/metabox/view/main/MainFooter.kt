package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import com.clone.metabox.ui.theme.LightBlue

@Composable
fun MainFooter (
    pageState: String,
    navigateToTheaterInfo: () -> Unit,
    navigateToMovieList: () -> Unit,
    navigateToBooking: () -> Unit,
    navigateToHome: () -> Unit,
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
                tint = if(pageState == MainPageNavGraph.home) MaterialTheme.colors.LightBlue else Color.Unspecified,
                fontColor = if(pageState == MainPageNavGraph.home) MaterialTheme.colors.LightBlue else Color.Black,
                modifier = Modifier.clickable {
                    navigateToHome()
                }
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_movie_black),
                category = "영화",
                modifier = Modifier.clickable {
                    navigateToMovieList()
                }
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_theater_black),
                category = "극장",
                modifier = Modifier.clickable {
                    navigateToTheaterInfo()
                }
            )
            IconBox(
                imagePainter = painterResource(id = R.drawable.icon_calendar_black),
                category = "예매",
                tint = if(pageState == MainPageNavGraph.booking) MaterialTheme.colors.LightBlue else Color.Unspecified,
                fontColor = if(pageState == MainPageNavGraph.booking) MaterialTheme.colors.LightBlue else Color.Black,
                modifier = Modifier.clickable {
                    navigateToBooking()
                }
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