package com.clone.metabox.view.theater

import com.clone.metabox.data.api.response.TheaterDetailResponse

data class TheaterUiState (
    val theaterDetail: TheaterDetailResponse = TheaterDetailResponse(),
    val theaterId: String = "",
    val theaterName: String = "",
)