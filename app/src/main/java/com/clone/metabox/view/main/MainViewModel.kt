package com.clone.metabox.view.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.clone.metabox.MovieListActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {
    fun navigateMovieList (context: Context) {
        val intent = Intent(context, MovieListActivity::class.java)

        context.startActivity(intent)
    }
}