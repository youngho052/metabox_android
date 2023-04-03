package com.clone.metabox.view.theaterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TheaterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    companion object {
        const val THEATER_NAME = "theaterName"
        const val THEATER_ID = "theaterId"
    }

    private val _theaterDetailUiState: MutableStateFlow<TheaterDetailUiState> =
        MutableStateFlow(TheaterDetailUiState())
    val theaterDetailUiState: StateFlow<TheaterDetailUiState>
        get() = _theaterDetailUiState

    init {
        _theaterDetailUiState.value = _theaterDetailUiState.value.copy(
            theaterName = savedStateHandle.get<String>(THEATER_NAME) ?: ""
        )
    }
}