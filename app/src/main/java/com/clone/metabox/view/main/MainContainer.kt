package com.clone.metabox.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.Gray
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContainer (
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

    GlideImage(
        imageModel = { "https://img.megabox.co.kr/static/mb/images/common/bg/bg-origin.png" },
        modifier = Modifier.fillMaxSize()
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        stickyHeader {
            MainHeader()
        }

        item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    EventContainer()
                    MainContents(
                        navigateMovieList = { mainViewModel.navigateMovieList(context = context) }
                    )
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
fun MainContents (
    navigateMovieList: () -> Unit,
) {
    val contentFeedList = listOf("#박스오피스", "#상영예정", "#돌비시네마", "단독", "클소", "#필소")
    val eventFeedList = listOf("메가Pick", "영화", "극장", "제휴/할인", "시사회/무대인사")

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .height(1000.dp)
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

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(end = 20.dp),
        ) {
            items(count = 15) {
                if(it + 1 != 15) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(300.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
                    ) {

                    }
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(300.dp)
                            .clickable {
                                navigateMovieList()
                            }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(60.dp)
                                    .border(1.dp, MaterialTheme.colors.Gray, CircleShape)
                            ) {
                                GlideImage(
                                    modifier = Modifier.size(24.dp),
                                    imageModel = { "https://img.megabox.co.kr/static/mb/images/main_renewal/puls_24.png" }
                                )
                            }

                            Text(
                                text ="더보기",
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.Gray
                            )
                        }

                    }
                }

            }
        }

        Text(
            text = "더 많은 영화보기 >",
            fontSize = 12.sp,
            color = MaterialTheme.colors.Gray,
            modifier = Modifier
                .clickable {
                    navigateMovieList()
                }
        )
    }
}