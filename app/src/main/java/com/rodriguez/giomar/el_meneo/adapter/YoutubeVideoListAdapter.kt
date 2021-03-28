package com.rodriguez.giomar.el_meneo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodriguez.giomar.el_meneo.databinding.YoutubeVideoListItemBinding
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.youtube_video_list_item.view.*

class YoutubeVideoListAdapter(val onVideoSelect: (YoutubeVideo)-> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "YoutubeVideoListAdapter"
    private var videos: List<YoutubeVideo> = ArrayList<YoutubeVideo>()
    fun setVideos(videos: List<YoutubeVideo>) {
        this.videos = videos
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoListAdapter.YoutubeViewHolder {
        return YoutubeViewHolder(
            YoutubeVideoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is YoutubeViewHolder -> {
                holder.bind(videos[position])
            }
        }
    }

    override fun getItemCount() = videos.size

     inner class YoutubeViewHolder(itemView: YoutubeVideoListItemBinding): RecyclerView.ViewHolder(itemView.root), View.OnClickListener {
        private lateinit var selectedVideo: YoutubeVideo
        init {
            itemView.videoCard.setOnClickListener(this)
        }
        fun bind(video: YoutubeVideo) {
            selectedVideo = video
            itemView.tvTitle.text = video.title
            Picasso.get().load(video.videoCoverImageUrl).into(itemView.ivCover);
        }

        override fun onClick(v: View?) {
            onVideoSelect(selectedVideo)
        }
    }
}