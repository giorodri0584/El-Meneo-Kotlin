package com.rodriguez.giomar.el_meneo.ui.homeScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun FeedCard() {
    Surface(

    ) {
        Column(
        ) {
            Image(
                painter = rememberGlidePainter("https://scontent-atl3-2.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/182851756_303426231311606_6566337153389771401_n.jpg?tp=1&_nc_ht=scontent-atl3-2.cdninstagram.com&_nc_cat=1&_nc_ohc=FNL-K95Sq9YAX9T4KAl&edm=AGenrX8BAAAA&ccb=7-4&oh=cf42ea0dbd5edc3ed32e0f6b612877e6&oe=60972EC0&_nc_sid=5eceaa"),
                contentDescription = "video.title",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "This is a really long text",
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }

}