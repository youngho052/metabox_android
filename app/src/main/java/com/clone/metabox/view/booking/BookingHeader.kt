package com.clone.metabox.view.booking

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import com.clone.metabox.ui.theme.DarkPurple
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.ui.theme.LightPurple
import com.clone.metabox.util.onClick
import com.clone.metabox.view.common.AgeRestrictionBox
import com.clone.metabox.view.common.IconView

@Composable
fun BookingHeader(
    bookingType: String,
    theaterList: ArrayList<String>
) {
    Column {
        CommonHeader(bookingType = bookingType)
        if(bookingType == "movie") {
            BookingMovieList()
        }
        BookingTheaterList(
            theaterList = theaterList,
            bookingType = bookingType
        )
    }
}

@Composable
fun CommonHeader(
    bookingType: String
) {
    val context = LocalContext.current as ComponentActivity

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(MaterialTheme.colors.DarkPurple)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            IconView(
                painter = painterResource(id = R.drawable.icon_back_white),
                description = "${R.drawable.icon_back_white}",
                modifier = Modifier.onClick {
                    context.finish()
                }
            )

            Row(
                verticalAlignment= Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colors.LightGray, RoundedCornerShape(13.dp))
                    .padding(5.dp)
            ) {
                Text(
                    text = if(bookingType == "movie") "영화변경" else "극장변경",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Text(
                text = if(bookingType == "movie") "영화별 예매" else "극장별 예매",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BookingMovieList () {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.DarkPurple)
            .padding(start = 18.dp, end = 18.dp)
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(count = 30) {
                Box(modifier = Modifier
                    .width(80.dp)
                    .height(115.dp)
                    .border(1.dp, Color.Red)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AgeRestrictionBox(age = "19")
            Text(
                text = "존 윅4",
                color = Color.White,
                fontSize = 23.sp
            )
        }
    }

}

@Composable
fun BookingTheaterList (
    theaterList: ArrayList<String>,
    bookingType: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement= Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colors.DarkPurple)
            .padding(start = 18.dp, end = 18.dp)
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
//            contentPadding = PaddingValues(start = 18.dp, end = 18.dp),
            modifier = Modifier.let {
                when(bookingType) {
                    "theater" -> Modifier.fillMaxWidth()
                    "movie" -> Modifier.fillMaxWidth(0.8f)
                    else -> Modifier
                }
            }
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
                ) {
                    Text(
                        text = "${theaterList[it]}",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                    )
                }
            }
        }

        if(bookingType == "movie") {
            Text(
                text = "극장 변경",
                color = MaterialTheme.colors.LightGray,
                fontSize = 13.sp,
            )
        }
    }
}