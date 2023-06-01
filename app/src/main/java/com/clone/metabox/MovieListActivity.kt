package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.clone.metabox.util.RouteNavigation
import com.clone.metabox.view.movielist.MovieListContainer
import com.clone.metabox.view.movielist.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity : ComponentActivity() {
    @Inject
    lateinit var routeNavigation: RouteNavigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieListContainer(
                routeNavigation = routeNavigation,
            )
        }
    }
}
