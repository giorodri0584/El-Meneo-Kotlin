package com.rodriguez.giomar.el_meneo.views

import android.content.pm.ActivityInfo
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
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.adapter.RelatedVideosAdapter
import com.rodriguez.giomar.el_meneo.databinding.FragmentYoutubeVideoPlayerBinding
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel
import com.squareup.picasso.Picasso

class YoutubeVideoPlayerFragment : Fragment() {
    private val TAG = "YoutubeVideoPlayerFragment"
    private var _binding: FragmentYoutubeVideoPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    private val args by navArgs<YoutubeVideoPlayerFragmentArgs>()
    private lateinit var relatedAdapter: RelatedVideosAdapter
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mYoutubePlayer: YouTubePlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYoutubeVideoPlayerBinding.inflate(inflater, container, false)
        val selectedVideo = args.selectedVideo

        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        addFullScreenListenerToPlayer()
        initRecyclerView()
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                mYoutubePlayer = youTubePlayer
                val videoId = selectedVideo.videoId
                youTubePlayer.loadVideo(videoId, 0.toFloat())
            }
        })


        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        sharedModel.relatedVideos.observe(viewLifecycleOwner, Observer { videos ->
            val relatedVideos: List<YoutubeVideo> = videos.shuffled().take(15)
            relatedAdapter.addHeaderAndSubmitList(relatedVideos, selectedVideo.title)
        })

        sharedModel.showInterstitialAd.observe(viewLifecycleOwner, Observer { load ->
            if(load) {
                showInterstitialAd()
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
    private fun initRecyclerView() {
        binding.rvRelatedVideos.apply {
            layoutManager = LinearLayoutManager(context)
            relatedAdapter = RelatedVideosAdapter() { selectedVideo ->
                //sharedModel.setRelatedVideos(model.videos.value!!)
                sharedModel.loadInterstitialAd()
                findNavController().popBackStack(R.id.action_youtubeVideoPlayerFragment_self, true)
                val action = YoutubeVideoPlayerFragmentDirections.actionYoutubeVideoPlayerFragmentSelf(selectedVideo)
                findNavController().navigate(action)
            }
            adapter = relatedAdapter
        }
    }
    private fun showInterstitialAd() {
        mInterstitialAd = InterstitialAd(context)
        //production ad unit
        mInterstitialAd.adUnitId = "ca-app-pub-6174585484194945/6112193571"
        //test ad unit
        //mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mYoutubePlayer.pause()
                mInterstitialAd.show()
            }

            override fun onAdFailedToLoad(p0: Int) {

            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                mYoutubePlayer.play()
            }
        }
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