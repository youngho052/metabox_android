package com.clone.metabox.view.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.DarkPurple
import com.clone.metabox.ui.theme.Gray
import com.clone.metabox.ui.theme.LightBlack
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.view.common.CommonLine

@Composable
fun MovieSectionContainer() {
    var sectionState by remember { mutableStateOf("information") }

    fun changeSectionState (section: String) {
        sectionState = section
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
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
            "information" -> MovieInformation()
        }
    }
}

@Composable
fun MovieInformation () {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp)
    ) {
        MovieInformationForm(title = "개봉", description = "2023.03.08")
        MovieInformationForm(title = "타입", description = "2D ATMOS(자막), 2D Dolby(자막), 2D(더빙), 2D(자막)")
        MovieInformationForm(title = "장르", description = "애니메이션 / 122 분")
        MovieInformationForm(title = "등급", description = "12세이상관람가")
        CommonLine(MaterialTheme.colors.LightBlack)
        MovieInformationForm(title = "감독", description = "신카이 마코토")
        MovieInformationForm(
            title = "출연",
            description = "하라 나노카(이와토 스즈메 역), 마츠무라 호쿠토(무나카타 소타 역), 후카츠 에리(이와토 타마키 역), 마츠모토하쿠오(무나카타히츠지로역), 소메타니 쇼타(오카베 미노루 역), 이토 사이리(니노미야 루미 역), 하나세 코토네(아마베 치카 역), 하나자와 카나(이와토 츠바메 역), 카미키 류노스케(세리자와 토모야 역)"
        )
        CommonLine(MaterialTheme.colors.LightBlack)
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
fun MovieInformationForm (
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

}