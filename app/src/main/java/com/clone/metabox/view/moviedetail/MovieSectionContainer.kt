package com.clone.metabox.view.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.data.api.response.MovieDetailResponse
import com.clone.metabox.ui.theme.*
import com.clone.metabox.view.common.HorizontalLineView
import com.clone.metabox.view.common.StarRatingBar

@Composable
fun MovieDetailSectionContainer(
    movieDetailInformation: MovieDetailResponse
) {
    var sectionState by remember { mutableStateOf("information") }

    fun changeSectionState (section: String) {
        sectionState = section
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(if(sectionState == "information") 15.dp else 0.dp),
        modifier = Modifier
            .defaultMinSize(minHeight = 500.dp)
            .fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = 1f
                    val sizeWidth = size.width - strokeWidth
                    val sizeHeight = size.height - strokeWidth

                    drawLine(
                        color = Color.White,
                        start = Offset(0f, 0f),
                        end = Offset(sizeWidth, 0f),
                        strokeWidth = strokeWidth
                    )

                    drawLine(
                        color = Color.White,
                        start = Offset(0f, sizeHeight),
                        end = Offset(sizeWidth, sizeHeight),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(45.dp)
                    .drawBehind {
                        val strokeWidth = 3f
                        val sizeWidth = size.width - strokeWidth
                        val sizeHeight = size.height - strokeWidth

                        if (sectionState == "information") {
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, sizeHeight),
                                end = Offset(sizeWidth, sizeHeight),
                                strokeWidth = strokeWidth
                            )
                        }
                    }
                    .clickable {
                        changeSectionState("information")
                    }
            ) {
                Text(
                    text = "영화정보",
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(45.dp)
                    .drawBehind {
                        val strokeWidth = 3f
                        val sizeWidth = size.width - strokeWidth
                        val sizeHeight = size.height - strokeWidth

                        if (sectionState == "review") {
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, sizeHeight),
                                end = Offset(sizeWidth, sizeHeight),
                                strokeWidth = strokeWidth
                            )
                        }
                    }
                    .clickable {
                        changeSectionState("review")
                    }
            ) {
                Text(
                    text = "실관람평",
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }

        when(sectionState) {
            "information" -> MovieInformation(
                movieDetailInformation = movieDetailInformation
            )
            "review" -> MovieReviews()
        }
    }
}

@Composable
fun MovieInformation (
    movieDetailInformation: MovieDetailResponse
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp)
    ) {
        MovieInformationView(title = "개봉", description = "${movieDetailInformation.openingDate}")
        MovieInformationView(title = "타입", description = "${movieDetailInformation.type}")
        MovieInformationView(title = "장르", description = "${movieDetailInformation.genre} / ${movieDetailInformation.runningTime} 분")
        MovieInformationView(title = "등급", description = "${movieDetailInformation.grade}세이상관람가")
        HorizontalLineView(MaterialTheme.colors.LightBlack)
        MovieInformationView(title = "감독", description = "신카이 마코토")
        MovieInformationView(
            title = "출연",
            description = "하라 나노카(이와토 스즈메 역), 마츠무라 호쿠토(무나카타 소타 역), 후카츠 에리(이와토 타마키 역), 마츠모토하쿠오(무나카타히츠지로역), 소메타니 쇼타(오카베 미노루 역), 이토 사이리(니노미야 루미 역), 하나세 코토네(아마베 치카 역), 하나자와 카나(이와토 츠바메 역), 카미키 류노스케(세리자와 토모야 역)"
        )
        HorizontalLineView(MaterialTheme.colors.LightBlack)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier
                .fillMaxWidth(0.49f)
                .defaultMinSize(minHeight = 95.dp)
                .background(MaterialTheme.colors.DarkPurple)
                .padding(start = 18.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "누적 관객 수",
                color = MaterialTheme.colors.LightGray,
                fontSize = 14.sp
            )
            Text(
                text = "214.1 만",
                color = Color.White,
                fontSize = 18.sp
            )
            Text(
                text = "개봉 15일차",
                color = MaterialTheme.colors.LightGray,
                fontSize = 14.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .defaultMinSize(minHeight = 95.dp)
                .background(MaterialTheme.colors.DarkPurple)
                .padding(10.dp)
        ) {
            Text(
                text = "일별 관객 수",
                color = MaterialTheme.colors.LightGray,
                fontSize = 14.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = "69,375",
                    color = Color.White,
                    fontSize = 18.sp
                )
                Column() {
                    Text(
                        text = "전일 대비",
                        color = MaterialTheme.colors.LightGray,
                        fontSize = 11.sp
                    )
                    Text(
                        text = "19.7%",
                        color = Color.Red,
                        fontSize = 11.sp
                    )
                }
            }

        }
    }
}

@Composable
private fun MovieInformationView (
    title: String,
    description: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.LightGray,
            fontSize = 15.sp
        )

        Text(
            text = description,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}

@Composable
fun MovieReviews () {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(top = 30.dp, bottom = 30.dp)
    ) {
        Text(
            text = "'스즈메의 문단속' 관람평",
            color = Color.White,
            fontSize = 24.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            StarRatingBar(
                rating = 4.5f, 
                spaceBetween = 5.dp,
                fillStar = ImageBitmap.imageResource(id = R.drawable.icon_star_fill),
                emptyStar = ImageBitmap.imageResource(id = R.drawable.icon_star_empty)
            )
            Text(
                text = "9",
                color = MaterialTheme.colors.LightBlue,
                fontSize = 24.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFCCCCCC),
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Text(
                text = "실관람평 쓰기",
                color = MaterialTheme.colors.LightGray,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp, start = 8.dp, end = 8.dp)
            )
        }

        Text(
            text = """
                관람일 기준 7일 이내 등록 시 50P 가 적립됩니다.
                포인트는 관람평 최대 10편 지급 가능합니다
            """.trimIndent(),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = Color.White
        )
    }
    ReviewsContainer()
}

@Composable
fun ReviewsContainer () {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .defaultMinSize(minHeight = 500.dp)
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 30.dp, start = 18.dp, end = 18.dp, bottom = 50.dp)
    ) {
        ReviewForm()
        HorizontalLineView(color = Color(0xFFE7E7E7))
        ReviewForm()
        HorizontalLineView(color = Color(0xFFE7E7E7))
        ReviewForm()
        HorizontalLineView(color = Color(0xFFE7E7E7))
        ReviewForm()
        HorizontalLineView(color = Color(0xFFE7E7E7))
    }
}

@Composable
fun ReviewForm () {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(MaterialTheme.colors.LightGray, CircleShape)
            )
            Text(text = "m", color = Color.Black)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = "박영호",
                        color = MaterialTheme.colors.LightGray,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "13분전",
                        color = MaterialTheme.colors.LightGray,
                        fontSize = 15.sp
                    )
                }
                StarRatingBar(
                    rating = 4.5f,
                    fillStar = ImageBitmap.imageResource(id = R.drawable.icon_star_fill_purple),
                    emptyStar = ImageBitmap.imageResource(id = R.drawable.icon_star_empty_white)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "영화 너무 너무 재미 있어요~!",
                    color = MaterialTheme.colors.LightBlack
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(20.dp))
                ) {
                    Text(
                        text = "연출",
                        color = Color(0xFF666666),
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(20.dp))
                ) {
                    Text(
                        text = "스토리",
                        color = Color(0xFF666666),
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
                    )
                }
            }
        }
    }
}