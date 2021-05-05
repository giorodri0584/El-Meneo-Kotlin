package com.rodriguez.giomar.el_meneo.ui.videoScreen

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.rodriguez.giomar.el_meneo.repository.YoutubeVideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideosScreenViewModel : ViewModel() {
    private  val TAG = "VideosScreenViewModel"
    val videos: MutableState<List<YoutubeVideo>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    var videosScrollPosition: Int = 0
    init {
        Log.d(TAG, "creating viewModel...")
        fetchYoutubeVideos()
    }
    private fun fetchYoutubeVideos() {
        val isEmpty = videos.value.isEmpty()
        Log.d(TAG, isEmpty.toString())
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideos: List<YoutubeVideo> = YoutubeVideoRepository.getYoutubeVideos()
            withContext(Dispatchers.Main){
                videos.value = fetchedVideos
                isLoading.value = false
            }
        }
    }
}

sealed class VideosState {
    data class Data(val videos: List<YoutubeVideo>): VideosState()
    object Loading: VideosState()
    object Error: VideosState()
}

class VideosScreenViewModelFactory(): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideosScreenViewModel() as T
    }
}