package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.util.NavigatePages
import com.clone.metabox.view.booking.BookingContents
import com.clone.metabox.view.common.IconView
import com.clone.metabox.view.movielist.MovieListNavState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainBookingContainer(
    navigatePages: NavigatePages
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GlideImage(
            imageModel = { "https://img.megabox.co.kr/static/mb/images/common/bg/bg-mymega-main.png" },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "당신의 마음을 채우는 \n두 시간의 이야기",
            color = Color.White,
            fontSize = 26.sp,
            lineHeight = 40.sp,
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp)
                .offset(y = 110.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .offset(y = 255.dp)
        ) {
            BookingContentsBox(
                summary = "어느 곳에서 \n보고 싶으세요?",
                title = "극장별 예매",
            )
            BookingContentsBox(
                summary = "어떤 영화를 \n보고 싶으세요?",
                title = "영화별 예매",
                modifier = Modifier.clickable {
                    navigatePages.navigateMovieList(MovieListNavState.movieBooking)
                }
            )
        }
    }

    MainHeader(contentTitle = "예매")
}

@Composable
fun BookingContentsBox (
    summary: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .width(185.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(25.dp)
    ) {
        Text(
            text = "$summary",
            color = Color(0xFF030303),
            fontSize = 16.sp,
            lineHeight = 20.sp,
        )
        Text(
            text = "$title",
            color = Color(0xFF030303),
            fontSize = 12.sp,
            lineHeight = 20.sp,
        )
        IconView(
            painter = painterResource(id = R.drawable.icon_theater_black),
            description = "${R.drawable.icon_theater_black}",
            tint = Color.Unspecified,
            size = 48
        )
    }
}