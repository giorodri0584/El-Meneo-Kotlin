package com.rodriguez.giomar.el_meneo.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo

class HomeScreenViewModel : ViewModel() {
    val videos: MutableState<List<YoutubeVideo>> = mutableStateOf(listOf())
}