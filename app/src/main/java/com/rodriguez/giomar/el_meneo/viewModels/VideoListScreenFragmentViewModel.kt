package com.rodriguez.giomar.el_meneo.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.GetAllYoutubeVideosQuery.YoutubeVideo
import com.rodriguez.giomar.el_meneo.repository.YoutubeVideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoListScreenFragmentViewModel : ViewModel() {
    private val _videos: MutableLiveData<List<YoutubeVideo>> by lazy {
        MutableLiveData<List<YoutubeVideo>>().also {
            fetchYoutubeVideos()
        }
    }
    //val videos: LiveData<List<>>
    private val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { true }
    }

    fun getVideos() = _videos
    fun getIsLoading() = isLoading
    private fun fetchYoutubeVideos() {
        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideos: List<YoutubeVideo> = YoutubeVideoRepository.getYoutubeVideos()
            for (video in fetchedVideos){
                Log.d("ViewModel", video.title)
            }
            withContext(Dispatchers.Main){
                _videos.value = fetchedVideos
                isLoading.value = false
            }
        }
    }
}