package com.clone.metabox.view.main

import android.content.Context

data class MainUiModel (
    val navigateMovieList: (Context) -> Unit = {},
    val navigateMovieInfo: (Context) -> Unit = {}
)