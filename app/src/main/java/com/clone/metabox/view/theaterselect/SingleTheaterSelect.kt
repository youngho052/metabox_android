package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.data.api.response.TheaterResponse
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.util.onClick
import com.clone.metabox.view.common.HorizontalLineView
import timber.log.Timber

@Composable
fun SingleTheaterSelectContainer(
    theaterSelectUiState: State<TheaterSelectUiState>
) {
    Timber.d("check theater information ${theaterSelectUiState.value.theaterInformation}")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TheaterSelectHeader()
        SingleTheaterListContainer(
            theaterInformation = theaterSelectUiState.value.theaterInformation,
            navigateToTheaterDetail = { theaterName: String ->
                theaterSelectUiState.value.navigateToTheaterDetail(theaterName)
            },
        )
    }
}

@Composable
private fun SingleTheaterListContainer (
    theaterInformation: List<TheaterResponse>,
    navigateToTheaterDetail: (String) -> Unit,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .background(Color.White)
                        .padding(start = 18.dp, end = 18.dp)
                        .clickable {
                            navigateToTheaterDetail(
                                theaterInformation[selectableTheaterIndex].items[count].theaterId
                            )
                        }
                ) {
                    Text(
                        text = "${theaterInformation[selectableTheaterIndex].items[count].name}",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                HorizontalLineView(color = Color(0xFFE8E8E8))
            }
        }
    }
}
