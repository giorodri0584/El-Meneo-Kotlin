package com.rodriguez.giomar.el_meneo.ui.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.FeedCard
import com.rodriguez.giomar.el_meneo.ui.homeScreen.components.HomeScreenMainList
import com.rodriguez.giomar.el_meneo.ui.theme.ElMeneoTheme

class HomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
                            HomeScreenMainList()
                        }
                    )
                }
            }
        }
    }
}