package com.rodriguez.giomar.el_meneo.ui.videoDetailsScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import com.rodriguez.giomar.el_meneo.views.YoutubeVideoPlayerFragment

@Composable
fun VideoDetailsScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            FragmentContainerView(context).apply {

            }
        },
        update = {  view ->
        }
    )
}