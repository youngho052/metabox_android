package com.clone.metabox.view.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clone.metabox.R
import com.clone.metabox.data.api.response.BoxOffice
import com.clone.metabox.data.api.response.RecommendMovie
import com.clone.metabox.ui.theme.Gray
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.ui.theme.Purple
import com.clone.metabox.view.common.IconView
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContainer (
    mainViewModel: MainViewModel,
) {
    val mainUiState = mainViewModel.mainUiState.collectAsState()
    val context = LocalContext.current

//    GlideImage(
//        imageModel = { "https://img.megabox.co.kr/static/mb/images/common/bg/bg-origin.png" },
//        modifier = Modifier.fillMaxSize()
//    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        stickyHeader {
            MainHeader(
                contentTitle = "metaBox"
            )
        }

        item {
//            Text(
//                text = "kakao login",
//                modifier =
//                    Modifier
//                        .size(50.dp)
//                        .border(1.dp, Color.Red)
//                        .clickable { mainViewModel.kakaoLoginHandle() }
//            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .defaultMinSize(minHeight = 800.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    )
                    .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BoxOfficeMovieContainer(
                    boxOffice = mainUiState.value.mainPageInformation.boxOffice,
                    navigateMovieList = { context: Context ->
                        mainUiState.value.navigateMovieList(context)
                    },
                    navigateMovieDetail = { context: Context, movieId: String ->
                        mainUiState.value.navigateMovieDetail(context, movieId)
                    }
                )

                MovieFeedContainer(
                    recommendMovie = mainUiState.value.mainPageInformation.recommandMovie,
                    navigateMovieDetail = { context: Context, movieId: String ->
                        mainUiState.value.navigateMovieDetail(context, movieId)
                    }
                )
            }
        }
    }
}

@Composable
fun BoxOfficeMovieContainer (
    boxOffice: List<BoxOffice>,
    navigateMovieList: (Context) -> Unit,
    navigateMovieDetail: (Context, String) -> Unit,
) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 20.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(end = 20.dp),
        ) {
            items(count = boxOffice.size) {
                Column(
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colors.LightGray, RoundedCornerShape(15.dp))
                ) {
                    GlideImage(
                        imageModel = { "${boxOffice[it].poster}" },
                        modifier = Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                            .clickable {
                                navigateMovieDetail(context, boxOffice[it].movieId)
                            }
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                            .padding(top = 10.dp, start = 12.dp, end = 10.dp)
                    ) {
                        Text(
                            text ="${boxOffice[it].titleKr}",
                            fontSize = 15.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = "예매율 22.7%",
                                color = MaterialTheme.colors.LightGray,
                                fontSize = 13.sp
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                IconView(
                                    painter = painterResource(id = R.drawable.icon_star_empty),
                                    description = "${R.drawable.icon_star_empty}",
                                    tint = MaterialTheme.colors.Purple,
                                    size = 14
                                )
                                Text(
                                    text = "9.5",
                                    color = MaterialTheme.colors.LightGray,
                                    fontSize = 13.sp
                                )
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(85.dp)
                                .height(30.dp)
                                .background(
                                    MaterialTheme.colors.LightBlue,
                                    RoundedCornerShape(8.dp)
                                )
                        ) {
                            Text(
                                text = "바로예매",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                if(it + 1 == 15) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(150.dp)
                            .height(300.dp)
                            .clickable {
                                navigateMovieList(context)
                            }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(60.dp)
                                    .border(1.dp, MaterialTheme.colors.Gray, CircleShape)
                            ) {
                                GlideImage(
                                    modifier = Modifier.size(24.dp),
                                    imageModel = { "https://img.megabox.co.kr/static/mb/images/main_renewal/puls_24.png" }
                                )
                            }

                            Text(
                                text ="더보기",
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.Gray
                            )
                        }
                    }
                }
            }
        }

        Text(
            text = "더 많은 영화보기 >",
            fontSize = 12.sp,
            color = MaterialTheme.colors.Gray,
            modifier = Modifier
                .clickable {
                    navigateMovieList(context)
                }
        )
    }
}

@Composable
fun MovieFeedContainer (
    recommendMovie: RecommendMovie,
    navigateMovieDetail: (Context, String) -> Unit
) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "추천영화",
            fontSize = 24.sp
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(260.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    navigateMovieDetail(context, recommendMovie._id)
                }
        ) {
            GlideImage(
                imageModel = { recommendMovie.posterBg },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(15.dp)
            ) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                ) {
                    GlideImage(
                        imageModel = { recommendMovie.posterBg  },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                ) {
                    Text(
                        text = "${recommendMovie.titleKr}",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "RECOMMEND HOT MOVIE",
                        color = MaterialTheme.colors.LightGray,
                        fontSize = 15.sp
                    )
                }
                Text("like")
            }
        }
    }
}

