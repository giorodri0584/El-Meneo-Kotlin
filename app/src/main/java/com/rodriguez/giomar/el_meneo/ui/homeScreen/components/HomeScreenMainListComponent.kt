package com.rodriguez.giomar.el_meneo.ui.homeScreen.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme

@Composable
fun HomeScreenMainListComponent (
    videos: List<YoutubeVideo>,
    onVideoSelect: (YoutubeVideo) -> Unit
){
    LazyColumn() {
        item {
            Text(
                text = "Historias",
                style = MaterialTheme.typography.body1,
            )
            StoriesListComponent()
        }
        items(videos) { video ->
            VideoCardComponent(video) { selectedVideo ->
                onVideoSelect(selectedVideo)
            }
        }
    }
}