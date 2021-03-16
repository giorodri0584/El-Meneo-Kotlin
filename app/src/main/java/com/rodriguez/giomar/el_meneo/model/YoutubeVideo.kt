package com.rodriguez.giomar.el_meneo.model

import com.google.gson.annotations.SerializedName

data class YoutubeVideo(
    @SerializedName("video_id")
    var videoId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("video_cover_image_url")
    var videoCoverImageUrl: String,
    @SerializedName("author_name")
    var channelName: String,
    @SerializedName("author_url")
    var channelUrl: String,
    @SerializedName("author_image_url")
    var channelImageUrl: String

)