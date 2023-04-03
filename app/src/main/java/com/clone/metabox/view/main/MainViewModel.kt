package com.clone.metabox.view.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.MovieListActivity
import com.clone.metabox.TheaterActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {
    private val _mainUiState: MutableStateFlow<MainUiModel> =
        MutableStateFlow(MainUiModel())
    val mainUiState: StateFlow<MainUiModel>
        get() = _mainUiState

    init {
        _mainUiState.value = _mainUiState.value.copy(
            navigateMovieList = { context -> navigateMovieList(context) },
            navigateMovieInfo = { context -> navigateMovieInfo(context) },
            navigateTheaterInfo = {context -> navigateTheaterInfo(context) }
        )
    }
    private fun navigateMovieList (context: Context) {
        val intent = Intent(context, MovieListActivity::class.java)

        context.startActivity(intent)
    }

    private fun navigateMovieInfo (context: Context) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        context.startActivity(intent)
    }

    private fun navigateTheaterInfo (context: Context) {
        val intent = Intent(context, TheaterActivity::class.java)

        context.startActivity(intent)
    }
}