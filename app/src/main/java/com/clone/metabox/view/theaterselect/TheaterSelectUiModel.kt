package com.clone.metabox.view.theaterselect

import android.content.Context

data class TheaterSelectUiState (
    val navigateTheaterDetail: (Context, String) -> Unit = { context, theaterName -> }
)

