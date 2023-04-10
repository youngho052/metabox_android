package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clone.metabox.util.NavigatePages
import com.clone.metabox.view.booking.BookingContainer
import com.clone.metabox.view.main.MainContainer
import com.clone.metabox.view.main.MainFooter
import com.clone.metabox.view.main.MainViewModel
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
                startDestination = "main"
            ) {
                composable("main") {
                    MainContainer(
                        mainViewModel = mainViewModel,
                    )
                }
                composable("booking") {
                    BookingContainer()
                }
            }

            MainFooter(
                navigateToTheaterInfo = { navigatePages.navigateTheaterInfo(this) },
                navigateToMovieList = { navigatePages.navigateMovieList(this) },
                navigateToBooking = { navController.navigate("booking") }
            )
//            MainContainer(
//                mainViewModel = mainViewModel,
//            )
        }
    }
}

