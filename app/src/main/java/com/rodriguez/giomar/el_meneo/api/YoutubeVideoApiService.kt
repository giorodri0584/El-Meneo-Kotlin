package com.rodriguez.giomar.el_meneo.api

import android.util.Log
import com.google.gson.Gson
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object YoutubeVideoApiService {
    private const val TAG = "YoutubeVideoApiService"
    private val gson: Gson = Gson()
    suspend fun getAllVideos() {
        val response: HttpResponse = MyKtorClient.client.get("${MyKtorClient.BASE_URL}/YoutubeVideos")
        //Log.d(TAG, response.readText())
        val videosArray = gson.fromJson<Array<YoutubeVideo>>(response.readText(), Array<YoutubeVideo>::class.java)
        val videos = ArrayList(videosArray.toMutableList())
        for (video in videos) {
            Log.d(TAG, video.title)
        }
    }
}