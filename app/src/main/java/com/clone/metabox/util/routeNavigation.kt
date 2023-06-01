package com.clone.metabox.util

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.clone.metabox.*
import com.clone.metabox.data.api.response.TheaterItems
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouteNavigation @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private lateinit var bookingTypes: String
    companion object {
        const val SINGLE_SELECTOR = "single"
        const val MULTIPLY_SELECTOR = "multiply"
    }

    private val theaterIdList: ArrayList<String> = arrayListOf()
    private val theaterNameList: ArrayList<String> = arrayListOf()

    private fun addIntentFlags (intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun navigateToMovieList (listState: String) {
        val intent = Intent(context, MovieListActivity::class.java)

        intent.putExtra("movieNavToState", listState)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateToMovieDetail (movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateToSingleTheaterSelector () {
        val intent = Intent(context, TheaterSelectActivity::class.java)

        intent.putExtra("theaterState", SINGLE_SELECTOR)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateToMultiTheaterSelector (
        movieId: String?,
        bookingType: String,
    ) {
        val intent = Intent(context, TheaterSelectActivity::class.java)
        bookingTypes = bookingType

        intent.putExtra("theaterState", MULTIPLY_SELECTOR)
        intent.putExtra("theaterMovieId", movieId)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateToTheaterDetail (
        theaterId: String,
    ) {
        val intent = Intent(context, TheaterActivity::class.java)

        intent.putExtra("theaterId", theaterId)
        addIntentFlags(intent)

        context.startActivity(intent)
    }

    fun navigateToBooking (
        theaterList: List<TheaterItems>,
    ) {
        val intent = Intent(context, BookingActivity::class.java)

        repeat(theaterList.size) {
            theaterIdList.add(theaterList[it].theaterId)
            theaterNameList.add(theaterList[it].name)
        }

        intent.putStringArrayListExtra("theaterIdList", theaterIdList)
        intent.putStringArrayListExtra("theaterNameList", theaterNameList)
        intent.putExtra("bookingType", bookingTypes)
        addIntentFlags(intent)
//
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
