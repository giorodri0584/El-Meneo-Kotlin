package com.rodriguez.giomar.el_meneo.ui.homeScreen.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreenMainListComponent (
    videos: List<YoutubeVideo>,
    onVideoSelect: (YoutubeVideo) -> Unit
){
    LazyColumn() {
        items(videos) { video ->
            VideoCardComponent(video) { selectedVideo ->
                onVideoSelect(selectedVideo)
            }
        }
    }
}