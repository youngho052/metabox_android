package com.clone.metabox.view.theater

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.domain.theater.GetTheaterDetailUseCase
import com.clone.metabox.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TheaterViewModel @Inject constructor(
    private val theaterDetailUseCase: GetTheaterDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    companion object {
        const val THEATER_NAME = "theaterName"
        const val THEATER_ID = "theaterId"
    }

    private val _theaterDetailUiState: MutableStateFlow<TheaterUiState> =
        MutableStateFlow(TheaterUiState())
    val theaterDetailUiState: StateFlow<TheaterUiState>
        get() = _theaterDetailUiState

    private val theaterId
        get() = savedStateHandle.getStateFlow(THEATER_ID, "")

    init {
        _theaterDetailUiState.value = _theaterDetailUiState.value.copy(
            theaterName = savedStateHandle.get<String>(THEATER_ID) ?: ""
        )

        getTheaterDetails()
    }

    private fun getTheaterDetails () = viewModelScope.launch {
        if(theaterId.value != "") {
            theaterDetailUseCase(theaterId.value).collectLatest {
                Timber.d("check theater details ${it}")
                if(it is Result.Success) {
                    _theaterDetailUiState.value = _theaterDetailUiState.value.copy(
                        theaterDetail = it.data
                    )
                }
            }
        }
    }
}