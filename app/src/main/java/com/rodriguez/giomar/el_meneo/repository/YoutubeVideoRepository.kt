package com.rodriguez.giomar.el_meneo.repository

import android.util.Log
import com.rodriguez.giomar.el_meneo.api.MyRetrofitBuilder
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo

object YoutubeVideoRepository {
    const val TAG = "YoutubeVideoRepository"
    suspend fun getYoutubeVideos(): List<YoutubeVideo> {
        val videos = MyRetrofitBuilder.youtubeVideoApiService.getYoutubeVideos()
        //Log.d(TAG, videos.toString())
        return videos
    }
}