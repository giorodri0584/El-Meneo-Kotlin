package com.rodriguez.giomar.el_meneo.ui.homeScreen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.rodriguez.giomar.el_meneo.model.ImagePost


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedCard(
    post: ImagePost,
    onPostClick: () -> Unit
) {
    Surface(

    ) {
        Column(

        ) {
            // Display 10 items
            val pagerState = rememberPagerState(
                pageCount = post.imagesUrl.size,
                // We increase the offscreen limit, to allow pre-loading of images
                initialOffscreenLimit = 2,
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.body1
                )
            }
            HorizontalPager(
                state = pagerState,
            ) { page ->
                Image(
                    painter = rememberGlidePainter(
                        request = post.imagesUrl[page],
                        fadeIn = true,
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(290.dp)
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
            )


        }
    }
}