package com.rodriguez.giomar.el_meneo.ui.videoScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.ui.homeScreen.HomeScreenDirections
import com.rodriguez.giomar.el_meneo.ui.videoScreen.components.HomeScreenMainListComponent
import com.rodriguez.giomar.el_meneo.ui.videoScreen.components.LoadingComponent
import com.rodriguez.giomar.el_meneo.viewModels.VideoScreenViewModel
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel
import com.rodriguez.giomar.el_meneo.views.VideoListScreenFragmentDirections

class VideoScreen : Fragment() {
    private val TAG = "VideoScreen"
    private lateinit var model: VideoScreenViewModel
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    private lateinit var listState: LazyListState
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Loading compose screen")
        model = ViewModelProvider(requireActivity())[VideoScreenViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                val videos = model.videos.value
                val isLoading = model.isLoading.value
                listState = model.listState
                sharedModel.setRelatedVideos(videos)
                Scaffold (
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                "El Meneo",
                                color = Color.White
                            )
                        }, backgroundColor = colorResource(id = R.color.colorPrimary))
                    },
                    content = {
                        if(isLoading) {
                            LoadingComponent()
                        }
                        if (videos.isNotEmpty()) {
                            HomeScreenMainListComponent(videos, listState ) { selectedVideo ->
                                sharedModel.loadInterstitialAd()
                                val action = VideoScreenDirections.actionVideoScreenToYoutubeVideoPlayerFragment(selectedVideo)
                                //val action = VideoListScreenFragmentDirections.actionVideoListScreenFragmentToYoutubeVideoPlayerFragment(selectedVideo)
                                findNavController().navigate(action)
                            }
                        }
                    },
                )


            }
        }
    }

    override fun onDestroy() {
        model.listState = listState
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }
}