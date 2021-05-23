package com.rodriguez.giomar.el_meneo.ui.videoScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodriguez.giomar.el_meneo.viewModels.VideoScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun VideosScreenMainListComponent (
    videos: List<YoutubeVideo>,
    listState: LazyListState,
    onVideoSelect: (YoutubeVideo) -> Unit
){
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(videos) { video ->
            VideoCardComponent(video) { selectedVideo ->
                onVideoSelect(selectedVideo)
            }
        }
    }

}