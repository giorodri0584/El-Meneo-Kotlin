package com.rodriguez.giomar.el_meneo.api

import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import retrofit2.http.GET

interface YoutubeVideoApiService {
    @GET("youtube-videos")
    suspend fun getYoutubeVideos() : List<YoutubeVideo>
}