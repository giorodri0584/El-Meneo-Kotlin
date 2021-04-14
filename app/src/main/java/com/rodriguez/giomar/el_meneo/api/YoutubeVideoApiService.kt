package com.rodriguez.giomar.el_meneo.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object YoutubeVideoApiService {
    private const val TAG = "YoutubeVideoApiService"
    private val gson: Gson = Gson()
    suspend fun getAllVideos(): List<YoutubeVideo> {
        val response: HttpResponse = MyKtorClient.client.get("${MyKtorClient.BASE_URL}/YoutubeVideos?order=-updatedAt")
        //Log.d(TAG, response.readText())
        val json = gson.fromJson(response.readText(), JsonObject::class.java)
        val videosString = json.get("results")
        val videosArray = gson.fromJson(videosString, Array<YoutubeVideo>::class.java)
        return ArrayList(videosArray.toMutableList())
    }
}