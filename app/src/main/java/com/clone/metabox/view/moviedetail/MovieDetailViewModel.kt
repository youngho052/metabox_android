package com.clone.metabox.view.moviedetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(

):ViewModel() {
    private val _movieDetailUiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState())

    val movieDetailUiState: StateFlow<MovieDetailUiState>
        get() = _movieDetailUiState
}