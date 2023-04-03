package com.clone.metabox.view.theater

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.clone.metabox.TheaterDetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TheaterViewModel @Inject constructor(

): ViewModel() {
    private val _theaterUiState: MutableStateFlow<TheaterUiState> =
        MutableStateFlow(TheaterUiState())
    val theaterUiState: StateFlow<TheaterUiState>
        get() = _theaterUiState

    fun navigateTheaterDetail (
        context: Context,
        theaterName: String,
    ) {
        val intent = Intent(context, TheaterDetailActivity::class.java)

        intent.putExtra("theaterName", theaterName)

        context.startActivity(intent)
    }
}