package com.rodriguez.giomar.el_meneo.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rodriguez.giomar.el_meneo.viewModels.VideoListScreenFragmentViewModel
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel

class HomeScreen : Fragment() {
    private lateinit var model: VideoListScreenFragmentViewModel
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(this)[VideoListScreenFragmentViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                Text(
                    text = "Compose View"
                )
            }
        }
    }
}