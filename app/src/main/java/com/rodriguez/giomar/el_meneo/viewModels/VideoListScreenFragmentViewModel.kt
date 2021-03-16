package com.rodriguez.giomar.el_meneo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.rodriguez.giomar.el_meneo.repository.YoutubeVideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoListScreenFragmentViewModel : ViewModel() {
    private val videos: MutableLiveData<List<YoutubeVideo>> by lazy {
        MutableLiveData<List<YoutubeVideo>>().also {
            fetchYoutubeVideos()
        }
    }
    private val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { true }
    }

    fun getVideos() : LiveData<List<YoutubeVideo>> {
        return videos
    }
    fun getIsLoading() = isLoading
    private fun fetchYoutubeVideos() {
        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideos: List<YoutubeVideo> = YoutubeVideoRepository.getYoutubeVideos()
            withContext(Dispatchers.Main){
                videos.value = fetchedVideos
                isLoading.value = false
            }
        }
    }
}