package com.rodriguez.giomar.el_meneo.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.GetAllYoutubeVideosQuery
import com.rodriguez.giomar.el_meneo.extension.toYoutubeVideo
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
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
    val videos: LiveData<List<YoutubeVideo>> get() = _videos

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also { true }
    }
    val isLoading: LiveData<Boolean> get() = _isLoading

    private fun fetchYoutubeVideos() {
        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideos: List<GetAllYoutubeVideosQuery.YoutubeVideo> = YoutubeVideoRepository.getYoutubeVideos()
            val castVideos: MutableList<com.rodriguez.giomar.el_meneo.model.YoutubeVideo> = mutableListOf()
            for (video in fetchedVideos){
                Log.d("ViewModel", video.title)
                castVideos.add(video.toYoutubeVideo())
            }
            withContext(Dispatchers.Main){
                _videos.value = castVideos
                _isLoading.value = false
            }
        }
    }
}