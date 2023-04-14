package com.clone.metabox.util

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.clone.metabox.*
import com.clone.metabox.view.main.MainPageNavGraph
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatePages @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun navigateMovieList (listState: String) {
        val intent = Intent(context, MovieListActivity::class.java)

        intent.putExtra("movieNavToState", listState)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }

    fun navigateMovieDetail (movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }

    fun navigateSingleTheaterSelector () {
        val intent = Intent(context, TheaterSelectActivity::class.java)

        intent.putExtra("theaterState", "single")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }

    fun navigateMultiTheaterSelector (
        movieId: String?
    ) {
        val intent = Intent(context, TheaterSelectActivity::class.java)

        intent.putExtra("theaterState", "multiply")
        intent.putExtra("theaterMovieId", movieId)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }

    fun navigateTheaterDetail (
        theaterName: String,
    ) {
        val intent = Intent(context, TheaterActivity::class.java)

        intent.putExtra("theaterName", theaterName)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }

    fun navigateBooking () {
        val intent = Intent(context, BookingActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

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