package com.rodriguez.giomar.el_meneo.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.HomeScreenMainListComponent
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.LoadingComponent
import com.rodriguez.giomar.el_meneo.viewModels.HomeScreenViewModel
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel

class HomeScreen : Fragment() {
    private lateinit var model: HomeScreenViewModel
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                val videos = model.videos.value
                val isLoading = model.isLoading.value
                sharedModel.setRelatedVideos(videos)
                Scaffold (
                    topBar = {
                        TopAppBar(title = {
                            Text(
                            "El Meneo"
                            )
                                          }, backgroundColor = colorResource(id = R.color.colorPrimary))
                             },
                    content = {
                        if(isLoading) {
                            LoadingComponent()
                        }
                        if (videos.isNotEmpty()) {
                            HomeScreenMainListComponent(videos) { selectedVideo ->
                                sharedModel.loadInterstitialAd()
                                val action = HomeScreenDirections.actionHomeScreenToYoutubeVideoPlayerFragment(selectedVideo)
                                //val action = VideoListScreenFragmentDirections.actionVideoListScreenFragmentToYoutubeVideoPlayerFragment(selectedVideo)
                                findNavController().navigate(action)
                            }
                        }
                    },
                )


            }
        }
    }
}