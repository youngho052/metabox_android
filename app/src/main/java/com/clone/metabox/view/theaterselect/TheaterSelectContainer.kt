package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun TheaterSelectContainer (
    theaterSelectViewModel: TheaterSelectViewModel
) {
    val scrollState = rememberScrollState()

    when(theaterSelectViewModel.theaterState) {
        "single" -> SingleTheaterSelectContainer(theaterSelectViewModel = theaterSelectViewModel)
        "multiply" -> MultipleTheaterSelect(theaterSelectViewModel = theaterSelectViewModel)
    }
}

