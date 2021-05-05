package com.rodriguez.giomar.el_meneo.ui.videoScreen.components

import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.glide.rememberGlidePainter
import com.rodriguez.giomar.el_meneo.model.YoutubeVideo

@Composable
fun VideoCardComponent(
    video: YoutubeVideo,
    onVideoSelect: (YoutubeVideo) -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onVideoSelect(video) }
    ) {
        Column(
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = rememberGlidePainter(video.videoCoverImageUrl),
                    contentDescription = video.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Image(
                        painter = rememberGlidePainter(video.channelImageUrl),
                        contentDescription = video.channelName,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = video.channelName,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }

            }

        }


    }
}