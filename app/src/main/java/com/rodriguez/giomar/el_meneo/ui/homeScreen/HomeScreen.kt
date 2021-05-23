package com.rodriguez.giomar.el_meneo.ui.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.sampleData.SampleData
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.FeedCard
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.HomeScreenMainList
import com.rodriguez.giomar.el_meneo.ui.theme.ElMeneoTheme

class HomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val posts = SampleData.getImagePosts()
        return ComposeView(requireContext()).apply {
            setContent {
                ElMeneoTheme() {
                    Scaffold(
                        topBar = { TopAppBar(
                            title = {
                                Text(text = "Feed")
                            }
                        )},
                        content = {
                            HomeScreenMainList(posts) {
                                Log.d("Navigate", "Home Screen Navigate")
                                findNavController().navigate(R.id.action_homeScreen_to_postDetailsScreen)
                            }
                        }
                    )
                }
            }
        }
    }
}