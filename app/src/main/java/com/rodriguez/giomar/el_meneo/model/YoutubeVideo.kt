package com.rodriguez.giomar.el_meneo.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class YoutubeVideo(
    var videoId: String,
    var title: String,
    var videoCoverImageUrl: String,
    var channelName: String,
    var channelImageUrl: String,
    var channelUrl: String,
    var likesCount: Int,
    var viewCount: Int,
    var updatedAt: String,
    var commentsCount: Int
) : Parcelable