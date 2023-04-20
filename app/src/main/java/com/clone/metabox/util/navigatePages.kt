package com.clone.metabox.util

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
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
    private lateinit var bookingTypes: String
    companion object {
        const val SINGLE_SELECTOR = "single"
        const val MULTIPLY_SELECTOR = "multiply"
    }

    private fun addIntentFlags (intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun navigateMovieList (listState: String) {
        val intent = Intent(context, MovieListActivity::class.java)

        intent.putExtra("movieNavToState", listState)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateMovieDetail (movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateSingleTheaterSelector () {
        val intent = Intent(context, TheaterSelectActivity::class.java)

        intent.putExtra("theaterState", SINGLE_SELECTOR)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateMultiTheaterSelector (
        movieId: String?,
        bookingType: String,
    ) {
        val intent = Intent(context, TheaterSelectActivity::class.java)
        bookingTypes = bookingType

        intent.putExtra("bookingType", bookingTypes)
        intent.putExtra("theaterState", MULTIPLY_SELECTOR)
        intent.putExtra("theaterMovieId", movieId)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateTheaterDetail (
        theaterName: String,
    ) {
        val intent = Intent(context, TheaterActivity::class.java)

        intent.putExtra("theaterName", theaterName)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateBooking (
        theaterId: String,
        theaterName: ArrayList<String>
    ) {
        val intent = Intent(context, BookingActivity::class.java)

        intent.putExtra("theaterId", theaterId)
        intent.putStringArrayListExtra("theaterName", theaterName)
        intent.putExtra("bookingType", bookingTypes)
        addIntentFlags(intent)

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
