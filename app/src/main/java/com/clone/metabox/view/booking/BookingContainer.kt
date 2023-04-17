package com.clone.metabox.view.booking

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookingContainer(
    bookingViewModel: BookingViewModel
) {
    LazyColumn() {
        stickyHeader {
            BookingHeader(
                bookingType = "movie",
                theaterList = bookingViewModel.theaterName
            )
        }

        items(count = bookingViewModel.theaterName.size){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .border(1.dp, Color.Red)
            ) {
                Text("${bookingViewModel.theaterName[it]}")
            }
        }
    }
}



