package com.clone.metabox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clone.metabox.ui.theme.MetaboxTheme
import com.clone.metabox.view.theaterdetail.TheaterDetailContainer
import com.clone.metabox.view.theaterdetail.TheaterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheaterDetailActivity : ComponentActivity() {
    private val theaterDetailViewModel: TheaterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheaterDetailContainer(
                theaterDetailViewModel = theaterDetailViewModel
            )
        }
    }
}
