package com.clone.metabox.view.theater

import android.content.Context

data class TheaterUiState (
    val navigateTheaterDetail: (Context, String) -> Unit = { context, theaterName -> }
)

