package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clone.metabox.util.RouteNavigation
import com.clone.metabox.view.main.*
import com.clone.metabox.view.movielist.MovieListNavState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var routeNavigation: RouteNavigation
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
                        routeNavigation = routeNavigation
                    )
                }

                composable(MainPageNavGraph.booking) {
                    MainBookingContainer(
                        routeNavigation = routeNavigation
                    )
                }
            }

            MainFooter(
                pageState = mainViewModel.mainPageState.value,
                navigateToTheaterInfo = { routeNavigation.navigateToSingleTheaterSelector() },
                navigateToMovieList = { routeNavigation.navigateToMovieList(MovieListNavState.movieDetail) },
                navigateToBooking = {
                    routeNavigation.navigateToPageState(
                        navController,
                        MainPageNavGraph.booking,
                        mainViewModel.mainPageState
                    )
                },
                navigateToHome = {
                    routeNavigation.navigateToPageState(
                        navController,
                        MainPageNavGraph.home,
                        mainViewModel.mainPageState
                    )
                }
            )
        }
    }
}

