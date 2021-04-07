package com.rodriguez.giomar.el_meneo.viewModels.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo

class SharedYoutubeVideoViewModel : ViewModel() {
    private var adCounter: Int = 0
    private val _selectedYoutubeVideo: MutableLiveData<YoutubeVideo> by lazy {
        MutableLiveData<YoutubeVideo>()
    }
    val selectedYoutubeVideo: LiveData<YoutubeVideo> by lazy {
        _selectedYoutubeVideo
    }
    private val _relatedVideos: MutableLiveData<List<YoutubeVideo>> = MutableLiveData()
    val relatedVideos: LiveData<List<YoutubeVideo>> get() = _relatedVideos

    private val _showInterstitialAd: MutableLiveData<Boolean> = MutableLiveData(false)
    val showInterstitialAd: LiveData<Boolean> get() = _showInterstitialAd

    fun setRelatedVideos(videos: List<YoutubeVideo>) {
        _relatedVideos.value = videos
    }
    fun loadInsterstitialAd() {
        when(adCounter) {
            0 -> {
                _showInterstitialAd.value = true
                adCounter++
            }
            3 -> {
                adCounter = 0
            }
            else -> {
                adCounter++
            }
        }
    }
}