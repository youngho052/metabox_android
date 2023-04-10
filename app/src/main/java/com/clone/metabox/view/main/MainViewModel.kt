package com.clone.metabox.view.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.BookingActivity
import com.clone.metabox.MovieDetailActivity
import com.clone.metabox.MovieListActivity
import com.clone.metabox.TheaterSelectActivity
import com.clone.metabox.domain.auth.KakaoLoginUseCase
import com.clone.metabox.domain.main.GetMainPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.clone.metabox.result.Result
import com.clone.metabox.util.NavigatePages

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainPageUseCase: GetMainPageUseCase,
    private val kakaoLoginUseCase: KakaoLoginUseCase,
): ViewModel() {
    private val _mainUiState: MutableStateFlow<MainUiModel> =
        MutableStateFlow(MainUiModel())
    val mainUiState: StateFlow<MainUiModel>
        get() = _mainUiState

    private val navigatePages: NavigatePages = NavigatePages()

    init {
        _mainUiState.value = _mainUiState.value.copy(
            navigateMovieList = { context -> navigatePages.navigateMovieList(context) },
            navigateMovieDetail = { context, movieId -> navigatePages.navigateMovieDetail(context, movieId) },
            navigateTheaterInfo = { context -> navigatePages.navigateTheaterInfo(context) },
            navigateBooking = { context -> navigatePages.navigateBooking(context)  }
        )

        getMainPageInformation()
    }

    private fun getMainPageInformation() = viewModelScope.launch {
        getMainPageUseCase(Unit).collectLatest {
            if(it is Result.Success) {
                _mainUiState.value = _mainUiState.value.copy(
                    mainPageInformation = it.data
                )
            }
        }
    }

    fun kakaoLoginHandle () = viewModelScope.launch {
        kakaoLoginUseCase(Unit).collectLatest {
            Timber.d("kakao Login test $it")
        }
    }
}