package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.clone.metabox.R
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.view.common.IconTextView

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
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_home_black),
                category = "홈",
                tint = if(pageState == MainPageNavGraph.home) MaterialTheme.colors.LightBlue else Color.Unspecified,
                fontColor = if(pageState == MainPageNavGraph.home) MaterialTheme.colors.LightBlue else Color.Black,
                modifier = Modifier.clickable {
                    navigateToHome()
                }
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_movie_black),
                category = "영화",
                modifier = Modifier.clickable {
                    navigateToMovieList()
                }
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_theater_black),
                category = "극장",
                modifier = Modifier.clickable {
                    navigateToTheaterInfo()
                }
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_calendar_black),
                category = "예매",
                tint = if(pageState == MainPageNavGraph.booking) MaterialTheme.colors.LightBlue else Color.Unspecified,
                fontColor = if(pageState == MainPageNavGraph.booking) MaterialTheme.colors.LightBlue else Color.Black,
                modifier = Modifier.clickable {
                    navigateToBooking()
                }
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_profile_black),
                category = "마이페이지"
            )
        }
    }
}

