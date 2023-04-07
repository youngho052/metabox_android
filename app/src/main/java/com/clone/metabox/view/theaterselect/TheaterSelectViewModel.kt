package com.clone.metabox.view.theaterselect

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.clone.metabox.TheaterActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TheaterSelectViewModel @Inject constructor(

): ViewModel() {
    private val _theaterUiState: MutableStateFlow<TheaterSelectUiState> =
        MutableStateFlow(TheaterSelectUiState())
    val theaterUiState: StateFlow<TheaterSelectUiState>
        get() = _theaterUiState

    init {
        _theaterUiState.value = _theaterUiState.value.copy(
            navigateTheaterDetail = { context, theaterName -> navigateTheaterDetail(context,theaterName) }
        )
    }
    private fun navigateTheaterDetail (
        context: Context,
        theaterName: String,
    ) {
        val intent = Intent(context, TheaterActivity::class.java)

        intent.putExtra("theaterName", theaterName)

        context.startActivity(intent)
    }
}