package com.rodriguez.giomar.el_meneo.views

import android.content.pm.ActivityInfo
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.rodriguez.giomar.el_meneo.databinding.FragmentYoutubeVideoPlayerBinding
import com.rodriguez.giomar.el_meneo.utils.FullScreenHelper
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel
import com.squareup.picasso.Picasso

class YoutubeVideoPlayerFragment : Fragment() {
    private val TAG = "YoutubeVideoPlayerFragment"
    private var _binding: FragmentYoutubeVideoPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    private val args by navArgs<YoutubeVideoPlayerFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYoutubeVideoPlayerBinding.inflate(inflater, container, false)
        val selectedVideo = args.selectedVideo
        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        addFullScreenListenerToPlayer()
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = selectedVideo.videoId
                loadChannelImage(selectedVideo.channelImageUrl)
                binding.tvTitle.text = selectedVideo.title
                youTubePlayer.loadVideo(videoId, 0.toFloat())
            }
        })


        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }



        // Inflate the layout for this fragment
        return binding.root
    }
    private fun loadChannelImage(channelImageUrl: String) {
        Picasso.get().load(channelImageUrl).into(binding.ivChannel)
    }
    private fun addFullScreenListenerToPlayer() {
        binding.youtubePlayerView.addFullScreenListener(object: YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                hideSystemUI()
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                //fullScreenHelper.enterFullScreen();
            }

            override fun onYouTubePlayerExitFullScreen() {

                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                showSystemUI()
                //fullScreenHelper.exitFullScreen()
            }
        })
    }
    private fun hideSystemUI() {
        binding.topAppBar.visibility = View.GONE
        if (Build.VERSION.SDK_INT >= 30) {
            activity?.window?.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            }
        }else {
            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        binding.topAppBar.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= 30) {
            activity?.window?.insetsController?.apply {
                show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            }
        } else {
            activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
    }

    override fun onDestroy() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onDestroy()
    }
}