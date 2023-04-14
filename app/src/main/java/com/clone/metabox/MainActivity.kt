package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clone.metabox.util.NavigatePages
import com.clone.metabox.view.booking.BookingContainer
import com.clone.metabox.view.main.*
import com.clone.metabox.view.movielist.MovieListNavState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigatePages: NavigatePages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = MainPageNavGraph.home
            ) {
                composable(MainPageNavGraph.home) {
                    MainContainer(
                        mainViewModel = mainViewModel,
                        navigatePages = navigatePages
                    )
                }

                composable(MainPageNavGraph.booking) {
                    MainBookingContainer(
                        navigatePages = navigatePages
                    )
                }
            }

            MainFooter(
                pageState = mainViewModel.mainPageState.value,
                navigateToTheaterInfo = { navigatePages.navigateSingleTheaterSelector() },
                navigateToMovieList = { navigatePages.navigateMovieList(MovieListNavState.movieDetail) },
                navigateToBooking = {
                    navigatePages.navigateToPageState(
                        navController,
                        MainPageNavGraph.booking,
                        mainViewModel.mainPageState
                    )
                },
                navigateToHome = {
                    navigatePages.navigateToPageState(
                        navController,
                        MainPageNavGraph.home,
                        mainViewModel.mainPageState
                    )
                }
            )
        }
    }
}

