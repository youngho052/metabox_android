package com.clone.metabox.view.movielist

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.data.api.response.Movies
import com.clone.metabox.ui.theme.Gray
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.util.OnBottomReached
import com.clone.metabox.view.common.HorizontalLineView
import com.clone.metabox.view.common.IconView
import com.skydoves.landscapist.glide.GlideImage
import com.clone.metabox.R
import com.clone.metabox.util.onClick
import com.clone.metabox.view.common.AgeRestrictionBox
import com.skydoves.landscapist.ImageOptions
import okhttp3.internal.notifyAll
import timber.log.Timber

@Composable
fun MovieListContainer(
    movieListViewModel: MovieListViewModel
) {
    val movieListUiState = movieListViewModel.movieListUiState.collectAsState()

    when(movieListViewModel.movieNavToState) {
        "movieDetail" -> MovieDetailListContainer(movieListUiState)
        "movieBooking" -> MovieBookingListContainer(movieListUiState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovieDetailListContainer (
    movieListUiState: State<MovieListUiState>
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(25.dp),
    ) {
        stickyHeader {
            MovieListContainerHeader()
        }

        items(count = movieListUiState.value.movieList.movies.size) {
            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                MovieListView(
                    movies = movieListUiState.value.movieList.movies[it],
                    rank = it + 1,
                    navigateToMovieDetail = { movieId -> movieListUiState.value.navigateToMovieDetail(movieId) }
                )
                HorizontalLineView(color = MaterialTheme.colors.Gray)
            }
        }
    }

    if(movieListUiState.value.movieList.offset != 0) {
        listState.OnBottomReached {
            movieListUiState.value.loadMoreMovieList()
        }
    }
}

@Composable
private fun MovieBookingListContainer (
    movieListUiState: State<MovieListUiState>
) {
    val context = LocalContext.current as ComponentActivity

    val listState = rememberLazyGridState()

    Column() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "영화 선택",
                    fontSize = 18.sp
                )
            }

            IconView(
                painter = painterResource(id = R.drawable.icon_close_black),
                description = "${R.drawable.icon_close_black}",
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .onClick {
                        context.finish()
                    }
            )
        }

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            contentPadding = PaddingValues(top = 20.dp, start = 18.dp, end = 18.dp, bottom = 20.dp),
            columns = GridCells.Adaptive(minSize = 110.dp),
            state = listState
        ) {
            items(count = movieListUiState.value.movieList.movies.size) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .onClick {
                            movieListUiState.value.navigateToTheaterSelector(movieListUiState.value.movieList.movies[it].movieId)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(170.dp)
                    ) {
                        GlideImage(
                            imageModel = { movieListUiState.value.movieList.movies[it].poster },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            loading = {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colors.LightGray)
                                )
                            }
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 5.dp, end = 5.dp)
                        ) {
                            AgeRestrictionBox(
                                age = movieListUiState.value.movieList.movies[it].grade,
                            )
                        }
                    }

                    Text(
                        text = "${movieListUiState.value.movieList.movies[it].titleKr}",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    if(movieListUiState.value.movieList.offset != 0) {
        listState.OnBottomReached {
            movieListUiState.value.loadMoreMovieList()
        }
    }
}


@Composable
private fun MovieListView (
    movies: Movies,
    rank: Int,
    navigateToMovieDetail: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .height(155.dp)
            .clickable {
                navigateToMovieDetail(movies.movieId)
            }
    ) {
        Box(
            modifier = Modifier
                .width(105.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(5.dp))
        ) {
            GlideImage(
                imageModel = {"${movies.poster}"},
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                ),
                loading = {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.LightGray)
                    )
                }
            )
            Text(
                text ="$rank",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 6.dp, top = 2.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 5.dp, end = 5.dp)
            ) {
                AgeRestrictionBox(
                    age = movies.grade,
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(
                text = "${movies.titleKr}",
                fontSize = 16.sp,
                color = Color(0xFF030303)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                MovieInformationView(
                    title = "연령",
                    information = "${movies.grade}세 이용가",
                )
                MovieInformationView(title = "예매율", "55%")
                MovieInformationView(title = "개봉일", information = "${movies.openingDate}")
                MovieInformationView(
                    title = "실관람평",
                    information = "8.5",
                    color = MaterialTheme.colors.LightBlue
                )
            }
        }
    }
}

@Composable
private fun MovieInformationView (
    title: String,
    information: String,
    color: Color = Color.Black
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "$title",
            fontSize = 12.sp,
            color = MaterialTheme.colors.LightGray
        )
        Text(
            text = "$information",
            fontSize = 12.sp,
            color = color
        )
    }
}