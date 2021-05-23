package com.rodriguez.giomar.el_meneo.ui.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.rodriguez.giomar.el_meneo.model.ImagePost

@Composable
fun HomeScreenMainList(
    posts: List<ImagePost>,
    onPostClick: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { post ->
            FeedCard(post) {
                onPostClick()
            }
        }
    }
}