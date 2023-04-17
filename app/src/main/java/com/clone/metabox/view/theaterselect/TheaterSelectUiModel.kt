package com.clone.metabox.view.theaterselect

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class TheaterSelectUiState (
    val theaterList: SnapshotStateList<String> = mutableStateListOf(),
    val navigateToTheaterDetail: (String) -> Unit = {},
    val navigateToBooking: (String) -> Unit = {},
)

