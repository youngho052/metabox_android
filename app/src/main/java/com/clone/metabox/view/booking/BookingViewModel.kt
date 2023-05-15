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
    val theaterName: ArrayList<String>
        get() = checkNotNull(savedStateHandle.get<ArrayList<String>>("theaterName"))
//        get() = savedStateHandle.get<ArrayList<String>>("theaterName")?: arrayListOf()

    val bookingType: String
        get() = savedStateHandle.get<String>("bookingType")?: ""

//    val bookingTypes: StateFlow<String>
//        get() = savedStateHandle.getStateFlow("bookingType", "")

    init {
        Timber.d("check theater name $theaterName")
    }
}