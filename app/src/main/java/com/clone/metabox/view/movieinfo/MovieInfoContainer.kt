package com.clone.metabox.view.movieinfo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieInfoContainer() {
    LazyColumn() {
        stickyHeader {
            MovieInfoHeader()
        }

        item {
            MovieInfoContents()
        }
    }

    BottomBar()
}

@Composable
fun MovieInfoHeader () {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(0xFF120d3c))
    ) {
        Text("Movie Detail",color = Color.White)
    }
}

@Composable
fun MovieInfoContents () {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .height(screenHeight)
            .background(Color(0XFF150C1C))
    ) {
        GlideImage(
            imageModel = { "https://img.megabox.co.kr/SharedImg/2023/02/06/7gFJhWRalgHSihiVTu9oOoKRXxMH5mKe_720.jpg" },
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(475.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.White.copy(alpha = 0.1f), Color(0XFF150C1C)),
                        startY = 50f,
                        endY = 250f,
                    ),
                    alpha = 0.8f
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "스즈메의 문단속",
                color = Color.White,
                fontSize = 22.sp
            )

            Text(
                text = "Suzume",
                color = MaterialTheme.colors.LightGray,
                fontSize = 13.sp,
                fontWeight = FontWeight(700)
            )

            Text(
                text = "12세 이용가",
                color = MaterialTheme.colors.DarkGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight(700)
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text ="1위 (38.7%)",
                    color = Color.White,
                    fontSize = 15.sp
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text ="평점",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "9.8",
                        color = MaterialTheme.colors.Purple,
                        fontSize = 14.sp
                    )
                }
            }
        }

//        MovieDescriptionBox()
    }
}

@Composable
fun MovieDescriptionBox () {
    var moreClickAble: MutableState<Boolean> = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 18.dp, end = 18.dp)
    ) {
        Text(
            text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
            color = Color.White,
            fontSize = 15.sp,
        )
        Column() {
            Text(
                text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                color = MaterialTheme.colors.LightGray,
                fontSize = 15.sp,
            )
            Text(
                text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                color = MaterialTheme.colors.LightGray,
                fontSize = 15.sp,
            )
        }

        if(moreClickAble.value) {
            Column() {
                Text(
                    text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                    color = MaterialTheme.colors.LightGray,
                    fontSize = 15.sp,
                )
                Text(
                    text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                    color = MaterialTheme.colors.LightGray,
                    fontSize = 15.sp,
                )
            }
            Column() {
                Text(
                    text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                    color = MaterialTheme.colors.LightGray,
                    fontSize = 15.sp,
                )
                Text(
                    text = "'이 근처에 폐허 없니? 문을 찾고 있어'",
                    color = MaterialTheme.colors.LightGray,
                    fontSize = 15.sp,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(1.dp, MaterialTheme.colors.LightBlack, RoundedCornerShape(3.dp))
                .clickable {
                    moreClickAble.value = !moreClickAble.value
                }
        ) {
            Text(
                text = if(moreClickAble.value) "닫기" else "더보기",
                color = MaterialTheme.colors.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
fun BottomBar () {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(55.dp)
            ) {
                Text(text = "좋아요")
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(55.dp)
                    .background(Color(0xFF57A5AE))
            ) {
                Text("바로 예매", color = Color.White)
            }
        }
    }
}