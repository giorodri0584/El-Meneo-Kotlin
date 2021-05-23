package com.rodriguez.giomar.el_meneo.ui.postDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rodriguez.giomar.el_meneo.ui.postDetailsScreen.components.ImageList
import com.rodriguez.giomar.el_meneo.ui.theme.ElMeneoTheme

@Composable
fun PostDetailsScreenComposable(
    imagesUrl: List<String>,
    onArrowBackClicked: () -> Unit
) {
    ElMeneoTheme() {
        Scaffold(
            topBar = {TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = {
                        onArrowBackClicked()
                    }) {
                        // below line is use to
                        // specify navigation icon.
                        Icon(
                            Icons.Filled.ArrowBack,
                            "Back Icon",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.Black
            )
                     },
            content = {
                ImageList(imagesUrl)
            }
        )
    }
}