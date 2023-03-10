package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContainer () {
    GlideImage(
        imageModel = { "https://img.megabox.co.kr/static/mb/images/common/bg/bg-origin.png" },
        modifier = Modifier.fillMaxSize()
    )

    LazyColumn() {
        stickyHeader {
            MainHeader()
        }

        item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    EventContainer()
                    MainContents()
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.White)
                .border(1.dp, Color(0xFFE9E9E9))
        ) {

        }
    }
}

@Composable
fun MainHeader () {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xFF120d3c))
    ) {
        Text("MetaBox",color = Color.White)
    }
}

@Composable
fun EventContainer () {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
    ) {
        items(count = 10) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(70.dp)
                    .height(95.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(50.dp)
                    )
            ) {
                Text(text = "test text", color = Color.White)
            }
        }
    }
}

@Composable
fun MainContents () {
    val contentFeedList = listOf("#박스오피스", "#상영예정", "#돌비시네마", "단독", "클소", "#필소")
    val eventFeedList = listOf("메가Pick", "영화", "극장", "제휴/할인", "시사회/무대인사")

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            )
            .padding(top = 30.dp, start = 20.dp)
    ) {
        SlideFeedView(
            feedList = contentFeedList,
            contentList = null
        ) {}

        SlideFeedView(
            feedList = eventFeedList,
            contentList = null
        ) {}

        SlideFeedView(
            feedList = contentFeedList,
            contentList = null
        ) {}

        SlideFeedView(
            feedList = eventFeedList,
            contentList = null
        ) {}
    }
}