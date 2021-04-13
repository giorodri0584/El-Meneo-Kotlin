package com.rodriguez.giomar.el_meneo.repository

import android.util.Log
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo


object YoutubeVideoRepository {
    const val TAG = "YoutubeVideoRepository"
    suspend fun getYoutubeVideos(): List<YoutubeVideo> {
        return YoutubeVideoApiService.getAllVideos()
    }
}