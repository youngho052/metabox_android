package com.clone.metabox.view.movielist

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.util.onClick
import com.clone.metabox.view.common.IconView

@Composable
fun MovieListContainerHeader() {
    val context = LocalContext.current as ComponentActivity

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .height(55.dp)
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "영화",
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 18.dp, end = 18.dp)
        ) {
            IconView(
                painter = painterResource(id = R.drawable.icon_back_black),
                description = "${R.drawable.icon_back_black}",
                tint = Color.Unspecified,
                modifier = Modifier.onClick {
                    context.finish()
                }
            )
            IconView(
                painter = painterResource(id = R.drawable.icon_menu_black),
                description = "${R.drawable.icon_menu_black}",
                tint = Color.Unspecified
            )
        }
    }
}
