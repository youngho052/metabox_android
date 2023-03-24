package com.clone.metabox.view.moviedetail

import com.clone.metabox.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.view.common.IconView

@Composable
fun MovieDetailHeader (
    scrollPos: Int
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .let {
                if(scrollPos == 0) {
                    it.background(Color.Black.copy(alpha = 0f))
                } else {
                    it.background(Color.Black)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            IconView(
                painter = painterResource(id = R.drawable.icon_back_white),
                description = "${R.drawable.icon_back_white}",
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    modifier = Modifier
                        .width(65.dp)
                        .height(24.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.25f),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Red, CircleShape)
                    )
                    Text(
                        text = "상영중",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
                IconView(
                    painter = painterResource(id = R.drawable.icon_share_white),
                    description = "${R.drawable.icon_share_white}",
                )
            }
        }
    }
}