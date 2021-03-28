package com.rodriguez.giomar.el_meneo.api

import android.util.Log
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.rodriguez.giomar.el_meneo.GetAllYoutubeVideosQuery

object YoutubeVideoGraphqlService {
    suspend fun getAllYoutubeVideos(): List<GetAllYoutubeVideosQuery.YoutubeVideo> {
        val response: Response<GetAllYoutubeVideosQuery.Data> = MyApolloClient.apolloClient.query(GetAllYoutubeVideosQuery()).await()
        Log.d("LaunchList", "Success ${response?.data?.youtubeVideos}")
        return response?.data?.youtubeVideos!!
    }
}