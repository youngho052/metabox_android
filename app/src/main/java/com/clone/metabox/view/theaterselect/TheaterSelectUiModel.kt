package com.clone.metabox.view.theaterselect

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.clone.metabox.data.api.response.TheaterResponse

data class TheaterSelectUiState (
    val theaterInformation: List<TheaterResponse> = listOf(TheaterResponse()),
    val theaterList: SnapshotStateList<String> = mutableStateListOf(),
    val navigateToTheaterDetail: (String) -> Unit = {},
    val navigateToBooking: (String) -> Unit = {},
)

