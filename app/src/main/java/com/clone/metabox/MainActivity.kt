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
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navigatePages = NavigatePages()

            NavHost(
                navController = navController,
                startDestination = MainPageNavGraph.home
            ) {
                composable(MainPageNavGraph.home) {
                    MainContainer(
                        mainViewModel = mainViewModel,
                    )
                }

                composable(MainPageNavGraph.booking) {
                    MainBookingContainer()
                }
            }

            MainFooter(
                pageState = mainViewModel.mainPageState.value,
                navigateToTheaterInfo = { navigatePages.navigateTheaterInfo(this) },
                navigateToMovieList = { navigatePages.navigateMovieList(this) },
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

