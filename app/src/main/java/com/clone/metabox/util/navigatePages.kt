package com.clone.metabox.util

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.clone.metabox.BookingActivity
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.MovieListActivity
import com.clone.metabox.TheaterSelectActivity
import com.clone.metabox.view.main.MainPageNavGraph
import javax.inject.Singleton

@Singleton
class NavigatePages {
    fun navigateMovieList (context: Context) {
        val intent = Intent(context, MovieListActivity::class.java)

        context.startActivity(intent)
    }

    fun navigateMovieDetail (context: Context, movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)

        context.startActivity(intent)
    }

    fun navigateTheaterInfo (context: Context) {
        val intent = Intent(context, TheaterSelectActivity::class.java)

        context.startActivity(intent)
    }

    fun navigateBooking (context: Context) {
        val intent = Intent(context, BookingActivity::class.java)

        context.startActivity(intent)
    }

    fun navigateToPageState (
        navController: NavController,
        pageState: String,
        mainPageState: MutableState<String>
    ) {
        mainPageState.value = pageState

        navController.navigate(pageState) {
            popUpTo(0)
        }
    }
}