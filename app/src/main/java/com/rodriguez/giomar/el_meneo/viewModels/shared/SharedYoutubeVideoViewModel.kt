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

    fun setSelectedYoutubeVideo(video: YoutubeVideo) {
        _selectedYoutubeVideo.value = video
    }
}