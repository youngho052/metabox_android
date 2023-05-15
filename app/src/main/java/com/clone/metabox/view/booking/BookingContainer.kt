package com.clone.metabox.view.booking

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.LightBlack
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.ui.theme.Purple
import com.clone.metabox.view.common.HorizontalLineView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookingContainer(
    bookingViewModel: BookingViewModel
) {
    LazyColumn() {
        stickyHeader {
            BookingHeader(
                bookingType = bookingViewModel.bookingType,
                theaterList = bookingViewModel.theaterName
            )
        }

        items(count = bookingViewModel.theaterName.size){
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {
                BookingListContainer()
                HorizontalLineView(
                    color = Color(0xFFF5F5F5),
                    height = 10
                )
            }
        }
    }
}

@Composable
fun BookingListContainer () {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 18.dp, end = 18.dp)
    ) {
        Text(
            text = "강남대로(씨티)",
            color = MaterialTheme.colors.LightBlack,
            fontSize = 22.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "2D(자막)",
                color = Color.Black,
                fontSize = 13.sp
            )

            Text(
                text = "1관 230석",
                color = MaterialTheme.colors.LightGray,
                fontSize = 13.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        ) {
            Column(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colors.LightGray)
                    .padding(15.dp)
            ) {
                Text(
                    text = "20:25 ",
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    text = "~22:35",
                    color = Color.Black,
                    fontSize = 13.sp
                )
            }

            Text(
                text = "118석",
                color = MaterialTheme.colors.Purple,
                fontSize = 14.sp
            )
        }
    }
}



