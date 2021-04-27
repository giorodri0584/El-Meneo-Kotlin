package com.rodriguez.giomar.el_meneo.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.rodriguez.giomar.el_meneo.repository.YoutubeVideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel : ViewModel() {
    val videos: MutableState<List<YoutubeVideo>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    init {
        fetchYoutubeVideos()
    }
    private fun fetchYoutubeVideos() {
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