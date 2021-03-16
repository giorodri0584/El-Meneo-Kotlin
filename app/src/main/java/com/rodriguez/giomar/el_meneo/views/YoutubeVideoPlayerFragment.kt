package com.rodriguez.giomar.el_meneo.views

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.rodriguez.giomar.el_meneo.databinding.FragmentYoutubeVideoPlayerBinding
import com.rodriguez.giomar.el_meneo.utils.FullScreenHelper

class YoutubeVideoPlayerFragment : Fragment() {
    private var _binding: FragmentYoutubeVideoPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var fullScreenHelper: FullScreenHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYoutubeVideoPlayerBinding.inflate(inflater, container, false)
        fullScreenHelper = FullScreenHelper(activity)
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = "xeH1aWD9zOw"
                youTubePlayer.loadVideo(videoId, 0.toFloat())
            }
        })
        addFullScreenListenerToPlayer()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addFullScreenListenerToPlayer() {
        binding.youtubePlayerView.addFullScreenListener(object: YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                fullScreenHelper.enterFullScreen();
            }

            override fun onYouTubePlayerExitFullScreen() {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                fullScreenHelper.exitFullScreen()
            }
        })
    }

    override fun onDestroy() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onDestroy()
    }
}