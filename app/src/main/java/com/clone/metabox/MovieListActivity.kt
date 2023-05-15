package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.clone.metabox.view.movielist.MovieListContainer
import com.clone.metabox.view.movielist.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : ComponentActivity() {
    private val movieListViewModel: MovieListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieListContainer(
                movieListViewModel = movieListViewModel
            )
        }
    }
}
