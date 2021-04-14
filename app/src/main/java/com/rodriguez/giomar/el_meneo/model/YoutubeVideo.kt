package com.rodriguez.giomar.el_meneo.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class YoutubeVideo(
    @SerializedName("objectId")
    var objectId: String,
    @SerializedName("videoId")
    var videoId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("videoCoverImageUrl")
    var videoCoverImageUrl: String,
    @SerializedName("channelName")
    var channelName: String,
    @SerializedName("channelImageUrl")
    var channelImageUrl: String,
    @SerializedName("channelUrl")
    var channelUrl: String,
    @SerializedName("likesCount")
    var likesCount: Int,
    @SerializedName("viewCount")
    var viewCount: Int,
    @SerializedName("updatedAt")
    var updatedAt: String,
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("commentsCount")
    var commentsCount: Int
) : Parcelable