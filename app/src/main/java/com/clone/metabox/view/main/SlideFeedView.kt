package com.clone.metabox.view.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SlideFeedView (
    feedList: List<String>?,
    contentList: List<Any>?,
    content: @Composable () -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(count = feedList!!.size) {
            content
            Text(text = "${feedList!![it]}")
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(count = 10) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(300.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
            ) {

            }
        }
    }
}