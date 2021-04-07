package com.rodriguez.giomar.el_meneo.viewModels.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo

class SharedYoutubeVideoViewModel : ViewModel() {
    private val _selectedYoutubeVideo: MutableLiveData<YoutubeVideo> by lazy {
        MutableLiveData<YoutubeVideo>()
    }
    val selectedYoutubeVideo: LiveData<YoutubeVideo> by lazy {
        _selectedYoutubeVideo
    }
    private val _relatedVideos: MutableLiveData<List<YoutubeVideo>> = MutableLiveData()
    val relatedVideos: LiveData<List<YoutubeVideo>> get() = _relatedVideos

    fun setRelatedVideos(videos: List<YoutubeVideo>) {
        _relatedVideos.value = videos.shuffled().take(10)
    }
}