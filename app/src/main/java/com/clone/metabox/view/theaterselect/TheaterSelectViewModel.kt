package com.clone.metabox.view.theaterselect

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.clone.metabox.TheaterActivity
import com.clone.metabox.util.NavigatePages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TheaterSelectViewModel @Inject constructor(
    private val navigatePages: NavigatePages,
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

    private val theaterMovieId: String
        get() = savedStateHandle.get<String>("theaterMovieId") ?: ""

    val theaterList: SnapshotStateList<String> = mutableStateListOf()

    val navigateToPage
        get() = navigatePages

    init {
        _theaterUiState.value = _theaterUiState.value.copy(
            navigateToTheaterDetail = { theaterName -> navigatePages.navigateTheaterDetail(theaterName) },
        )
    }

    fun addTheaterList (theaterName: String) {
        if(theaterList.size < 3) {
            if(theaterName in theaterList) {
                deleteTheaterList(theaterName)
            } else {
                theaterList.add(theaterName)
            }
        } else if(theaterList.size == 3) {
            if(theaterName in theaterList) {
                deleteTheaterList(theaterName)
            }
        }
    }

    fun deleteTheaterList (theaterName: String) {
        theaterList.remove(theaterName)
    }
}