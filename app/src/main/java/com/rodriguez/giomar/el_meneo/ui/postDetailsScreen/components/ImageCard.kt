package com.rodriguez.giomar.el_meneo.ui.postDetailsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun ImageCard() {
    Surface(

    ) {
        Column(
        ) {
            Image(
                painter = rememberGlidePainter("https://picsum.photos/400"),
                contentDescription = "video.title",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
            )
        }
    }
}