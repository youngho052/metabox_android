package com.clone.metabox.view.booking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val theaterName: ArrayList<String>
        get() = savedStateHandle.get<ArrayList<String>>("theaterName")?: arrayListOf()

    init {
        Timber.d("check theater name $theaterName")
    }
}