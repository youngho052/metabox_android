package com.clone.metabox.view.theater

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.view.main.IconBox
import com.clone.metabox.R
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.view.common.CommonLine
import com.clone.metabox.view.common.IconView

@Composable
fun TheaterSelectContainer (
    theaterViewModel: TheaterViewModel
) {
    val theaterUiState = theaterViewModel.theaterUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TheaterSelectHeader()
        TheaterListContainer(
            navigateTheaterDetail = { context: Context, theaterName: String ->
                theaterViewModel.navigateTheaterDetail(context, theaterName)
            }
        )
    }
}

@Composable
fun TheaterSelectHeader () {
    val context = LocalContext.current as ComponentActivity

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            IconView(
                painter = painterResource(id = R.drawable.icon_back_black),
                description = "${R.drawable.icon_back_black}",
                tint = Color.Unspecified,
                size = 30,
                modifier = Modifier.clickable {
                    context.finish()
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "극장선택",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun TheaterListContainer (
    navigateTheaterDetail: (Context, String) -> Unit,
) {
    val context = LocalContext.current
    val list = listOf("강남", "강남대로(씨티)", "강동", "군자", "더 부티크 목동현대백화점", "동대문", "마곡", "목동", "상봉", "상암월드컵경기장")

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

        Column(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color(0xFFE8E8E8))
        ) {}
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(1f)
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
                            navigateTheaterDetail(context, list[count])
                        }
                ) {
                    Text(
                        text = "${list[count]}",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                CommonLine(color = Color(0xFFE8E8E8))
            }
        }
    }
}