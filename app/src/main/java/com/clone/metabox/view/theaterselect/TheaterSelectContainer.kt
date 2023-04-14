package com.clone.metabox.view.theaterselect

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import timber.log.Timber

@Composable
fun TheaterSelectContainer (
    theaterSelectViewModel: TheaterSelectViewModel
) {
    val theaterSelectUiState = theaterSelectViewModel.theaterUiState.collectAsState()
    val scrollState = rememberScrollState()

    when(theaterSelectViewModel.theaterState) {
        "single" -> SingleTheaterSelect(theaterSelectUiState = theaterSelectUiState)
        "multiply" -> MultipleTheaterSelect(theaterSelectViewModel = theaterSelectViewModel)
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .verticalScroll(scrollState)
//            .border(1.dp, Color.Red)
//    ) {
//        MultipleTheaterSelect(theaterSelectViewModel = theaterSelectViewModel)
//    }
}

