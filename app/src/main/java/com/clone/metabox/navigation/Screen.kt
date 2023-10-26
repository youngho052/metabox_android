package com.clone.metabox.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Booking : Screen("booking_screen")
}