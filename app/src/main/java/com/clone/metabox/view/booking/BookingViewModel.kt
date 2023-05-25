package com.clone.metabox.view.booking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val theaterNameList: ArrayList<String>
        get() = checkNotNull(savedStateHandle.get<ArrayList<String>>("theaterNameList"))

    val theaterIdList: ArrayList<String>
        get() = checkNotNull(savedStateHandle.get<ArrayList<String>>("theaterIdList"))

    val bookingType: String
        get() = savedStateHandle.get<String>("bookingType")?: ""

//    val bookingTypes: StateFlow<String>
//        get() = savedStateHandle.getStateFlow("bookingType", "")

    init {
        Timber.d("check theater name $theaterNameList")
    }
}