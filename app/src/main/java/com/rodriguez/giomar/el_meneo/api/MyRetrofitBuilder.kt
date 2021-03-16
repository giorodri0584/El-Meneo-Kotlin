package com.rodriguez.giomar.el_meneo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {
    const val BASE_URL = "https://el-meneo.herokuapp.com/";
    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val youtubeVideoApiService: YoutubeVideoApiService by lazy {
        retrofitBuilder
            .build()
            .create(YoutubeVideoApiService::class.java)
    }
}