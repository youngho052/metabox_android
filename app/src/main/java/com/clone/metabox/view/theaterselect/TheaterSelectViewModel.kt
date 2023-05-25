package com.clone.metabox.view.theaterselect

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.data.api.response.TheaterItems
import com.clone.metabox.domain.theater.GetTheaterInformationUseCase
import com.clone.metabox.util.RouteNavigation
import com.clone.metabox.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheaterSelectViewModel @Inject constructor(
    private val theaterInformationUseCase: GetTheaterInformationUseCase,
    private val routeNavigation: RouteNavigation,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _theaterUiState: MutableStateFlow<TheaterSelectUiState> =
        MutableStateFlow(TheaterSelectUiState())
    val theaterUiState: StateFlow<TheaterSelectUiState>
        get() = _theaterUiState
    companion object {
        const val SINGLE = "single"
        const val MULTIPLY = "multiply"
    }

    val theaterState: String
        get() = savedStateHandle.get<String>("theaterState") ?: ""

    val theaterList: SnapshotStateList<TheaterItems> = mutableStateListOf()

    val navigateToPage
        get() = routeNavigation

    init {
        _theaterUiState.value = _theaterUiState.value.copy(
            navigateToTheaterDetail = { theaterName -> routeNavigation.navigateTheaterDetail(theaterName) },
        )

        getTheaterInformation()
    }

    private fun getTheaterInformation () = viewModelScope.launch {
        theaterInformationUseCase(Unit).collectLatest {
            if(it is Result.Success) {
                _theaterUiState.value = _theaterUiState.value.copy(
                    theaterInformation = it.data
                )
            }
        }
    }

    fun addTheaterInformationList (theaterItems: TheaterItems) {
        if(theaterList.size < 3) {
            if(theaterItems in theaterList) {
                deleteTheaterInformationList(theaterItems)
            } else {
                theaterList.add(theaterItems)
            }
        } else if(theaterList.size == 3) {
            if(theaterItems in theaterList) {
                deleteTheaterInformationList(theaterItems)
            }
        }
    }

    fun deleteTheaterInformationList (theaterItems: TheaterItems) {
        theaterList.remove(theaterItems)
    }
}