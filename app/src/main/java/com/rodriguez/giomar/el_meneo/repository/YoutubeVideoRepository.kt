package com.rodriguez.giomar.el_meneo.repository

import android.util.Log
import com.rodriguez.giomar.el_meneo.GetAllYoutubeVideosQuery
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoGraphqlService


object YoutubeVideoRepository {
    const val TAG = "YoutubeVideoRepository"
    suspend fun getYoutubeVideos(): List<GetAllYoutubeVideosQuery.YoutubeVideo> {
        //val videos = MyRetrofitBuilder.youtubeVideoApiService.getYoutubeVideos()
        //Log.d(TAG, videos.toString())
        val videos = YoutubeVideoGraphqlService.getAllYoutubeVideos()
        return videos!!
    }
}