package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.clone.metabox.view.theater.TheaterContainer
import com.clone.metabox.view.theater.TheaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheaterActivity : ComponentActivity() {
    private val theaterViewModel: TheaterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheaterContainer(
                theaterViewModel = theaterViewModel
            )
        }
    }
}
