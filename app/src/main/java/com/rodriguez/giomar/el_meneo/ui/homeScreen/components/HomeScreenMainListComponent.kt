package com.rodriguez.giomar.el_meneo.ui.homeScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenMainListComponent (
    videos: List<YoutubeVideo>,
    onVideoSelect: (YoutubeVideo) -> Unit
){
    LazyColumn() {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Historias",
                    style = MaterialTheme.typography.body1,
                )
            }

            StoriesListComponent()
        }
        items(videos) { video ->
            VideoCardComponent(video) { selectedVideo ->
                onVideoSelect(selectedVideo)
            }
        }
    }
}