package com.clone.metabox.view.movielist

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.clone.metabox.data.api.response.MovieListResponse
import com.clone.metabox.data.api.response.Movies
import com.clone.metabox.ui.theme.Gray
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.util.OnBottomReached
import com.clone.metabox.view.common.CommonLine
import com.clone.metabox.view.common.IconView
import com.skydoves.landscapist.glide.GlideImage
import com.clone.metabox.R
import com.skydoves.landscapist.ImageOptions
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListContainer(
    movieListViewModel: MovieListViewModel
) {
    val movieListUiState = movieListViewModel.movieListUiState.collectAsState()

    val listState = rememberLazyListState()

    when(movieListViewModel.movieNavToState) {
        "movieDetail" ->
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                stickyHeader {
                    MovieListHeader()
                }

                items(count = movieListUiState.value.movieList.movies.size) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(25.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        MovieListContainer(
                            movies = movieListUiState.value.movieList.movies[it],
                            rank = it + 1,
                            navigateToMovieDetail = { movieId -> movieListUiState.value.navigateToMovieDetail(movieId) }
                        )
                        CommonLine(color = MaterialTheme.colors.Gray)
                    }
                }
            }
        "movieBooking" ->
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ){
                    Text("영화 선택")
                }
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(top = 20.dp, start = 18.dp, end = 18.dp),
                    columns = GridCells.Adaptive(minSize = 120.dp),
                ) {
                    items(count = movieListUiState.value.movieList.movies.size) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            GlideImage(
                                imageModel = { movieListUiState.value.movieList.movies[it].poster },
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(170.dp)
                            )
                            Text(
                                text = "${movieListUiState.value.movieList.movies[it].titleKr}",
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

    }


    if (
        movieListUiState.value.movieList.movies.isNotEmpty() &&
        (movieListUiState.value.movieList.offset < movieListUiState.value.movieList.moviesCount)
    ) {
        listState.OnBottomReached {
            movieListUiState.value.loadMoreMovieList()
        }
    }
}

@Composable
fun MovieListHeader() {
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
                modifier = Modifier.clickable {
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

@Composable
fun MovieListContainer (
    movies: Movies,
    rank: Int,
    navigateToMovieDetail: (String) -> Unit
) {
    val context = LocalContext.current

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
                    contentScale = ContentScale.Fit
                ),
            )
            Text(
                text ="$rank",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 6.dp, top = 2.dp)
            )
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
                MovieInformationForm(
                    title = "연령",
                    information = "${movies.grade}세 이용가",
                )
                MovieInformationForm(title = "예매율", "55%")
                MovieInformationForm(title = "개봉일", information = "${movies.openingDate}")
                MovieInformationForm(
                    title = "실관람평",
                    information = "8.5",
                    color = MaterialTheme.colors.LightBlue
                )
            }
        }
    }
}

@Composable
fun MovieInformationForm (
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