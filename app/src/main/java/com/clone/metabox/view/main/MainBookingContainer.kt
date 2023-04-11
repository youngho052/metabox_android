package com.clone.metabox.view.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.view.booking.BookingContents
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainBookingContainer() {
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
}