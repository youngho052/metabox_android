package com.clone.metabox.view.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clone.metabox.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashContainer (navController: NavController) {
    var startAnimation = remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation.value = true
        delay(4000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    AnimateSplashScreen(alphaAnimation = alphaAnimation.value)
}

@Composable
fun AnimateSplashScreen (
    alphaAnimation: Float
) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Meta Box",
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = 55.sp,
            modifier = Modifier
                .alpha(alphaAnimation)
        )
    }
}

//@Composable
//@Preview
//fun SplashContainerPreview() {
//    AnimateSplashScreen()
//}