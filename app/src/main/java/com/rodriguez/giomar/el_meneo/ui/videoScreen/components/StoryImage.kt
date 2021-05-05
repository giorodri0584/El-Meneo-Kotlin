package com.rodriguez.giomar.el_meneo.ui.videoScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage

@Composable
fun StoryImage(imageUrl: String) {
    val shape = CircleShape

        Box(
            modifier = Modifier
                .size(70.dp)
                .padding(3.dp)
                .background(color = Color.LightGray, shape = shape)
                .clip(shape)
                .border(2.dp, Color.Gray, CircleShape)
        ) {
            GlideImage(
                data = imageUrl,
                contentDescription = "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
}