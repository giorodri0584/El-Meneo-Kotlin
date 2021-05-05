package com.rodriguez.giomar.el_meneo.ui.videoScreen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.rodriguez.giomar.el_meneo.ui.videoScreen.components.HomeScreenMainListComponent
import com.rodriguez.giomar.el_meneo.ui.videoScreen.components.LoadingComponent

@Composable
fun VideosScreen(
    listState: LazyListState,
    onVideoSelected: (YoutubeVideo) -> Unit
) {
    val model: VideosScreenViewModel = viewModel(factory = VideosScreenViewModelFactory())
    val isLoading = model.isLoading.value
    val videos = model.videos.value
    if(isLoading) {
        LoadingComponent()
    }
    if (videos.isNotEmpty()) {
        HomeScreenMainListComponent(videos, listState ) { selectedVideo ->
            //sharedModel.loadInterstitialAd()
            //val action = VideoScreenDirections.actionVideoScreenToYoutubeVideoPlayerFragment(selectedVideo)
            //val action = VideoListScreenFragmentDirections.actionVideoListScreenFragmentToYoutubeVideoPlayerFragment(selectedVideo)
            //findNavController().navigate(action)
            onVideoSelected(selectedVideo)
        }
    }
}