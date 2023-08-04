package com.clone.metabox.view.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.metabox.data.api.response.MainPageResponse
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

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainPageUseCase: GetMainPageUseCase,
    private val kakaoLoginUseCase: KakaoLoginUseCase,
): ViewModel() {
    private val _mainUiState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState())
    val mainUiState: StateFlow<MainViewState>
        get() = _mainUiState

    val mainPageState = mutableStateOf(MainPageNavGraph.home)

    init {
        getMainPageInformation()
    }

    // api init 하지 않고 람다 함수 또는 바로 적용 가능함
//    private val getMainPageInformation = run {
//        viewModelScope.launch {
//            getMainPageUseCase(Unit).collectLatest {
//                if (it is Result.Success) {
//                    _mainUiState.value = _mainUiState.value.copy(
//                        mainPageInformation = it.data
//                    )
//                }
//            }
//        }
//    }

    private fun getMainPageInformation() = viewModelScope.launch {
        getMainPageUseCase(Unit).collectLatest {
            if(it is Result.Success) {
                _mainUiState.value = _mainUiState.value.copy(
                    mainPageInformation = it.data
                )
            }
        }
    }
}

data class MainViewState(
    val mainPageInformation: MainPageResponse = MainPageResponse(),
    val mainPageState: String = MainPageNavGraph.home,
)