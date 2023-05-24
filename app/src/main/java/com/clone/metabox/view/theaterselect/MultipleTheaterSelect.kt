package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightPurple
import com.clone.metabox.view.common.HorizontalLineView
import com.clone.metabox.view.common.IconView
import com.clone.metabox.R
import com.clone.metabox.data.api.response.TheaterItems
import com.clone.metabox.data.api.response.TheaterResponse
import com.clone.metabox.util.onClick

@Composable
fun MultipleTheaterSelect(
    theaterSelectViewModel: TheaterSelectViewModel
) {
    val theaterSelectUiState = theaterSelectViewModel.theaterUiState.collectAsState()

    theaterSelectUiState.value.theaterInformation
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TheaterSelectHeader()
        MultiSelectorTheaterList(
            theaterList = theaterSelectViewModel.theaterList,
            deleteTheaterList = {
                    theaterItems: TheaterItems -> theaterSelectViewModel.deleteTheaterInformationList(theaterItems)
            }
        )

        MultipleTheaterListContainer(
            theaterList = theaterSelectViewModel.theaterList,
            theaterInformation  = theaterSelectUiState.value.theaterInformation,
            addTheaterList = {
                    theaterItems: TheaterItems -> theaterSelectViewModel.addTheaterInformationList(theaterItems)
            }
        )
    }

    MultiplyTheaterFooter(
        theaterList = theaterSelectViewModel.theaterList,
        navigateToBooking = {
            theaterList: List<TheaterItems> ->
                theaterSelectViewModel.navigateToPage.navigateBooking(theaterList)
        }
    )
}

@Composable
fun MultipleTheaterListContainer (
    theaterList: SnapshotStateList<TheaterItems>,
    theaterInformation: List<TheaterResponse>,
    addTheaterList: (TheaterItems) -> Unit,
) {
    var selectableTheaterIndex by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.35f)
        ) {
            items(count = theaterInformation.size) {count: Int ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .let {
                            if(count == selectableTheaterIndex) {
                                it.background(MaterialTheme.colors.LightBlue)
                            } else {
                                it.background(Color.White)
                            }
                        }
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(start = 18.dp, end = 18.dp)
                        .onClick {
                            selectableTheaterIndex = count
                        }
                ) {
                    Text(
                        text = "${theaterInformation[count].state}",
                        color = if(count == selectableTheaterIndex) Color.White else Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${theaterInformation[count].items.size}",
                        color = if(count == selectableTheaterIndex) Color.White else Color.Black,
                        fontSize = 14.sp
                    )
                }
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
            items(count = theaterInformation[selectableTheaterIndex].items.size) { count ->
                val itemsInformation = theaterInformation[selectableTheaterIndex].items[count]

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .let {
                            if (itemsInformation in theaterList) {
                                Modifier.background(MaterialTheme.colors.LightBlue)
                            } else {
                                Modifier.background(Color.White)
                            }
                        }
                        .fillMaxWidth()
                        .height(42.dp)
                        .clickable {
                            addTheaterList(
                                theaterInformation[selectableTheaterIndex].items[count]
                            )
                        }
                ) {
                    Text(
                        text = "${itemsInformation.name}",
                        color = if(itemsInformation in theaterList) { Color.White } else { Color.Black },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 18.dp, end = 18.dp)
                    )
                }

                if(count != selectableTheaterIndex){
                    HorizontalLineView(color = Color(0xFFE8E8E8))
                }

//                if(list.size == count + 1) {
//                    Box(modifier = Modifier.height(50.dp))
//                }
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
    theaterList: SnapshotStateList<TheaterItems>,
    deleteTheaterList: (TheaterItems) -> Unit
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
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(start = 18.dp, end = 18.dp)
            ) {
                items(count = theaterList.size) {
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
                            text = "${theaterList[it].name}",
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
    theaterList: SnapshotStateList<TheaterItems>,
    navigateToBooking: (List<TheaterItems>) -> Unit,
) {
//    val arrayList: ArrayList<TheaterItems> = arrayListOf()
//
//    theaterList.filter {
//        arrayList.add(it)
//    }

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
                        Modifier
                            .background(MaterialTheme.colors.LightPurple)
                            .onClick {
                                navigateToBooking(theaterList.toList())
                            }
                    } else {
                        Modifier
                            .background(Color(0xFFCCCCCC))
                            .onClick { }
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