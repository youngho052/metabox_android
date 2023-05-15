package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.view.common.HorizontalLineView

@Composable
fun SingleTheaterSelect(
    theaterSelectUiState: State<TheaterSelectUiState>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TheaterSelectHeader()
        SingleTheaterListContainer(
            navigateToTheaterDetail = { theaterName: String ->
                theaterSelectUiState.value.navigateToTheaterDetail(theaterName)
            },
        )
    }
}

@Composable
fun SingleTheaterListContainer (
    navigateToTheaterDetail: (String) -> Unit,
) {
    val list = listOf("강남", "강남대로(씨티)", "강동", "군자", "더 부티크 목동현대백화점", "동대문", "마곡", "목동", "상봉", "상암월드컵경기장"
        ,"강남", "강남대로(씨티)", "강동", "군자", "더 부티크 목동현대백화점", "동대문", "마곡", "목동", "상봉", "상암월드컵경기장",
        "강남", "강남대로(씨티)", "강동", "군자", "더 부티크 목동현대백화점", "동대문", "마곡", "목동", "상봉", "상암월드컵경기장")

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.35f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(MaterialTheme.colors.LightBlue)
                    .padding(start = 18.dp, end = 18.dp)
            ) {
                Text(
                    text = "서울",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "10",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color(0xFFE8E8E8))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            items(count = list.size) { count ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .background(Color.White)
                        .padding(start = 18.dp, end = 18.dp)
                        .clickable {
                            navigateToTheaterDetail(list[count])
                        }
                ) {
                    Text(
                        text = "${list[count]}",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                HorizontalLineView(color = Color(0xFFE8E8E8))
            }
        }
    }
}
