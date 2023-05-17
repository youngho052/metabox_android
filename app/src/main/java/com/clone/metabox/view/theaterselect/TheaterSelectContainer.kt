package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun TheaterSelectContainer (
    theaterSelectViewModel: TheaterSelectViewModel
) {
    val theaterSelectUiState = theaterSelectViewModel.theaterUiState.collectAsState()
    val scrollState = rememberScrollState()

    when(theaterSelectViewModel.theaterState) {
        "single" -> SingleTheaterSelectContainer(theaterSelectUiState = theaterSelectUiState)
        "multiply" -> MultipleTheaterSelect(theaterSelectViewModel = theaterSelectViewModel)
    }
}

