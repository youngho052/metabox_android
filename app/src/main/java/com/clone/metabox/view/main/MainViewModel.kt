package com.clone.metabox.view.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.MovieListActivity
import com.clone.metabox.TheaterActivity
import com.clone.metabox.domain.main.GetMainPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.clone.metabox.result.Result
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainPageUseCase: GetMainPageUseCase
): ViewModel() {
    private val _mainUiState: MutableStateFlow<MainUiModel> =
        MutableStateFlow(MainUiModel())
    val mainUiState: StateFlow<MainUiModel>
        get() = _mainUiState

    init {
        _mainUiState.value = _mainUiState.value.copy(
            navigateMovieList = { context -> navigateMovieList(context) },
            navigateMovieDetail = { context, movieId -> navigateMovieDetail(context, movieId) },
            navigateTheaterInfo = {context -> navigateTheaterInfo(context) }
        )

        getMainPageInformation()
    }

    private fun getMainPageInformation() = viewModelScope.launch {
        println("check api result ")
        getMainPageUseCase(Unit).collectLatest {
            println("check api result $it")
            if(it is Result.Success) {
                _mainUiState.value = _mainUiState.value.copy(
                    mainPageInformation = it.data
                )
            }
        }
    }
    private fun navigateMovieList (context: Context) {
        val intent = Intent(context, MovieListActivity::class.java)

        context.startActivity(intent)
    }

    private fun navigateMovieDetail (context: Context, movieId: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)

        intent.putExtra("movieId", movieId)

        context.startActivity(intent)
    }

    private fun navigateTheaterInfo (context: Context) {
        val intent = Intent(context, TheaterActivity::class.java)

        context.startActivity(intent)
    }
}