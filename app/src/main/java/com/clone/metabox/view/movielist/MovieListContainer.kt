package com.clone.metabox.view.movielist

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.data.api.response.MovieListResponse
import com.clone.metabox.data.api.response.Movies
import com.clone.metabox.ui.theme.Gray
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.util.OnBottomReached
import com.clone.metabox.view.common.CommonLine
import com.skydoves.landscapist.glide.GlideImage
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListContainer(
    movieListViewModel: MovieListViewModel
) {
    val movieListUiState = movieListViewModel.movieListUiState.collectAsState()

    val listState = rememberLazyListState()

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
                MovieListContents(
                    movies = movieListUiState.value.movieList.movies[it],
                    navigateMovieDetail = { context, movieId -> movieListUiState.value.navigateMovieDetail(context, movieId) }
                )
                CommonLine(color = MaterialTheme.colors.Gray)
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
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.White)
    ) {
        Text(
            text = "영화",
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Composable
fun MovieListContents (
    movies: Movies,
    navigateMovieDetail: (Context, String) -> Unit
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navigateMovieDetail(context, movies.movieId)
            }
    ) {
        GlideImage(
            imageModel = {"${movies.poster}"},
            modifier = Modifier
                .width(105.dp)
                .height(155.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(155.dp)
        ) {
            Text(
                text = "${movies.titleKr}",
                fontSize = 16.sp,
                color = Color(0xFF030303)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text ="예매율",
                    fontSize = 12.sp,
                    color = Color(0xFF999999)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "개봉일",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.LightGray
                    )
                    Text(
                        text = "${movies.openingDate}",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "실관람평",
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = "${movies.grade}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.LightBlue
                    )
                }
            }
        }
//        GlideImage(
//            modifier = Modifier
//                .width(105.dp)
//                .height(135.dp)
//                .border(1.dp, Color.Black),
//            imageModel = { /*TODO*/ }
//        )
    }
}