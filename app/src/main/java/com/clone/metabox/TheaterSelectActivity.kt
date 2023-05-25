package com.clone.metabox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.clone.metabox.view.theaterselect.TheaterSelectContainer
import com.clone.metabox.view.theaterselect.TheaterSelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheaterSelectActivity : ComponentActivity() {
    private val theaterSelectViewModel: TheaterSelectViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheaterSelectContainer(
                theaterSelectViewModel = theaterSelectViewModel
            )
        }
    }
}
