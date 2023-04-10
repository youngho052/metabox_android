package com.clone.metabox.view.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.view.common.IconView
import com.clone.metabox.view.main.MainFooter
import com.clone.metabox.view.main.MainHeader
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BookingContainer() {
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
            BookingContents(
                summary = "어느 곳에서 \n보고 싶으세요?",
                title = "극장별 예매"
            )
            BookingContents(
                summary = "어떤 영화를 \n보고 싶으세요?",
                title = "영화별 예매"
            )
        }
    }
    
    MainHeader(contentTitle = "예매")
//    MainFooter(
//        navigateToTheaterInfo = { /*TODO*/ },
//        navigateToMovieList = { /*TODO*/ },
//        navigateToBooking = { /*TODO*/ }
//    )
}

@Composable
fun BookingContents (
    summary: String,
    title: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
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