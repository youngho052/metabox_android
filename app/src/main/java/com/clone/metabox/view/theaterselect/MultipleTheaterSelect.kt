package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightPurple
import com.clone.metabox.view.common.CommonLine
import com.clone.metabox.view.common.IconView
import com.clone.metabox.view.movielist.onClick
import com.clone.metabox.R

@Composable
fun MultipleTheaterSelect(
    theaterSelectViewModel: TheaterSelectViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TheaterSelectHeader()
        MultiSelectorTheaterList(
            theaterList = theaterSelectViewModel.theaterList,
            deleteTheaterList = { theaterName: String -> theaterSelectViewModel.deleteTheaterList(theaterName) }
        )

        MultipleTheaterListContainer(
            theaterList = theaterSelectViewModel.theaterList,
            addTheaterList = { theaterName: String -> theaterSelectViewModel.addTheaterList(theaterName) }
        )
    }

    MultiplyTheaterFooter(
        theaterList = theaterSelectViewModel.theaterList
    )
}

@Composable
fun MultipleTheaterListContainer (
    theaterList: SnapshotStateList<String>,
    addTheaterList: (String) -> Unit,
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
                        .let {
                            if (list[count] in theaterList) {
                                Modifier.background(MaterialTheme.colors.LightBlue)
                            } else {
                                Modifier.background(Color.White)
                            }
                        }
                        .fillMaxWidth()
                        .height(42.dp)
                        .clickable {
                            addTheaterList(list[count])
                        }
                ) {
                    Text(
                        text = "${list[count]}",
                        color = if(list[count] in theaterList) { Color.White } else { Color.Black },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    )
                }

                if(list[count] !in theaterList){
                    CommonLine(color = Color(0xFFE8E8E8))
                }

                if(list.size == count +1 ) {
                    Box(modifier = Modifier.height(50.dp))
                }
            }
        }

//        Column(
//            modifier = Modifier.fillMaxWidth(1f)
//        ) {
//            repeat(1) {
//                Column(
//                    modifier = Modifier
//                        .height(800.dp)
//                        .padding(bottom = 70.dp)
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    repeat(list.size) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Start,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(42.dp)
//                                .background(Color.White)
//                                .padding(start = 18.dp, end = 18.dp)
//                        ) {
//                            Text(
//                                text = "${list[it]}",
//                                color = Color.Black,
//                                fontSize = 14.sp
//                            )
//                        }
//                        CommonLine(color = Color(0xFFE8E8E8))
//                    }
//                }
//            }
//        }
    }
}


@Composable
fun MultiSelectorTheaterList (
    theaterList: SnapshotStateList<String>,
    deleteTheaterList: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        if(theaterList.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(theaterList.size) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.LightPurple,
                                RoundedCornerShape(20.dp)
                            )
                            .onClick {
                                deleteTheaterList(theaterList[it])
                            }
                    ) {
                        Text(
                            text = "${theaterList[it]}",
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                        )
                        IconView(
                            painter = painterResource(id = R.drawable.icon_close_black),
                            description = "${R.drawable.icon_close_black}",
                            size = 17,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            }

        } else {
            Text("최대 극장 3개까지 선택 가능 합니다.")
        }
    }
}

@Composable
fun MultiplyTheaterFooter (
    theaterList: SnapshotStateList<String>
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .let {
                    if (theaterList.isNotEmpty()) {
                        Modifier.background(MaterialTheme.colors.LightPurple)
                    } else {
                        Modifier.background(Color(0xFFCCCCCC))
                    }
                }
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text(
                text = "선택 완료",
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}