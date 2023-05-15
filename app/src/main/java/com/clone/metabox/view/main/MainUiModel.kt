package com.clone.metabox.view.main

import android.content.Context
import com.clone.metabox.data.api.response.BoxOffice
import com.clone.metabox.data.api.response.MainPageResponse
import com.clone.metabox.data.api.response.PopUp
import com.clone.metabox.data.api.response.RecommendMovie

data class MainUiState (
    val mainPageInformation: MainPageResponse = MainPageResponse(),
    val mainPageState: String = MainPageNavGraph.home,
)