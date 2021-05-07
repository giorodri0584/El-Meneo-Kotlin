package com.rodriguez.giomar.el_meneo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.databinding.YoutubeVideoListItemBinding
import com.rodriguez.giomar.el_meneo.databinding.YoutubeVideoScreenListHeaderBinding
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.youtube_video_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1


class RelatedVideosAdapter(val onVideoSelect: (YoutubeVideo) -> Unit) : ListAdapter<DataItem, RecyclerView.ViewHolder>(YoutubeVideoDiffCallback()){
    private val TAG = "RelatedVideosAdapter"

    fun addHeaderAndSubmitList(list: List<YoutubeVideo>, title: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val items = when (list) {
                null -> listOf(DataItem.Header(title))
                else -> listOf(DataItem.Header(title)) + list.map { DataItem.VideoItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }


    class VideoViewHolder(private val binding: YoutubeVideoListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var selectedVideo: YoutubeVideo
        private lateinit var onVideoSelect: (YoutubeVideo) -> Unit
        init {
            binding.videoCard.setOnClickListener(this)
        }
        fun bind(video: YoutubeVideo, onVideoSelect: (YoutubeVideo) -> Unit) {
            selectedVideo = video
            this.onVideoSelect = onVideoSelect
            binding.tvTitle.text = video.title
            binding.tvChannelName.text = video.channelName
            Picasso.get().load(video.videoCoverImageUrl).into(binding.ivCover);
            Picasso.get().load(video.channelImageUrl).into(binding.ivChannel);
        }

        override fun onClick(v: View?) {
            onVideoSelect(selectedVideo)
        }

        companion object {
            fun from(parent: ViewGroup): VideoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = YoutubeVideoListItemBinding.inflate(layoutInflater, parent, false)
                return VideoViewHolder(binding)
            }
        }
    }

    class TitleViewHolder(private val binding: YoutubeVideoScreenListHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.tvTitle.text = title
        }
        companion object {
            fun from(parent: ViewGroup) : TitleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = YoutubeVideoScreenListHeaderBinding.inflate(layoutInflater, parent, false)
                return TitleViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TitleViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> VideoViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is VideoViewHolder -> {
                val item = getItem(position) as DataItem.VideoItem
                holder.bind(item.video, onVideoSelect = onVideoSelect)
            }
            is TitleViewHolder -> {
                val item = getItem(position) as DataItem.Header
                holder.bind(title = item.title)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.VideoItem -> ITEM_VIEW_TYPE_ITEM
            else -> throw ClassCastException("Unknown viewType ${position}")
        }
    }
}

sealed class DataItem {
    abstract val id: String
    data class VideoItem(val video: YoutubeVideo) : DataItem() {
        override val id: String = video.videoId
    }
    data class Header(val title: String): DataItem() {
        override val id = Long.MIN_VALUE.toString()
    }
}

class YoutubeVideoDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}
