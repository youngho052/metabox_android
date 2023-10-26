package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clone.metabox.navigation.NavGraph
import com.clone.metabox.navigation.Screen
import com.clone.metabox.ui.theme.MetaboxTheme
import com.clone.metabox.util.RouteNavigation
import com.clone.metabox.view.main.*
import com.clone.metabox.view.movielist.MovieListNavState
import com.clone.metabox.view.splash.SplashContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var routeNavigation: RouteNavigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MetaboxTheme {
                val navController = rememberNavController()
                val mainUiState = mainViewModel.mainUiState.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashContainer(navController = navController)
                    }

                    composable(Screen.Home.route) {
                        MainContainer(
                            mainViewModel = mainViewModel,
                            routeNavigation = routeNavigation
                        )
                    }

                    composable(Screen.Booking.route) {
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
                            Screen.Booking.route,
                            mainViewModel.mainPageState
                        )
                    },
                    navigateToHome = {
                        routeNavigation.navigateToPageState(
                            navController,
                            Screen.Home.route,
                            mainViewModel.mainPageState
                        )
                    }
                )
            }
        }
    }
}

