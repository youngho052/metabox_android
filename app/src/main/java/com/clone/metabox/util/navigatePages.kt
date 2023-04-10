package com.clone.metabox.util

import android.content.Context
import android.content.Intent
import com.clone.metabox.BookingActivity
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.MovieListActivity
import com.clone.metabox.TheaterSelectActivity
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
}