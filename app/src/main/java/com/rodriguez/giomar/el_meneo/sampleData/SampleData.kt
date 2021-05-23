package com.rodriguez.giomar.el_meneo.sampleData

import com.rodriguez.giomar.el_meneo.model.ImagePost

object SampleData {
    fun getImagePosts(): List<ImagePost> {
        val imagePosts = mutableListOf<ImagePost>()
        imagePosts.add(
            ImagePost(
                postId = "id1",
                title = "Image Post 1",
                featureImageUrl = "https://static.billboard.com/files/media/ozuna-bad-bunny-latin-2020-billboard-u-1548-compressed.jpg",
                imagesUrl = listOf(
                    "https://i.guim.co.uk/img/media/c2cf46f33a1dd3927bfe8d00b46b9ce296b2eadc/0_723_3047_1827/master/3047.jpg?width=465&quality=45&auto=format&fit=max&dpr=2&s=9d672d83772bbcf8b17f4aae6cdb3d22",
                    "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F20%2F2020%2F10%2F26%2Fbad-bunny4-2000.jpg&q=85",
                    "https://static.billboard.com/files/media/ozuna-bad-bunny-latin-2020-billboard-u-1548-compressed.jpg"
                   )
            )
        )
        imagePosts.add(
            ImagePost(
                postId = "id1",
                title = "Image Post 1",
                featureImageUrl = "https://static.billboard.com/files/media/ozuna-bad-bunny-latin-2020-billboard-u-1548-compressed.jpg",
                imagesUrl = listOf(
                    "https://i.guim.co.uk/img/media/c2cf46f33a1dd3927bfe8d00b46b9ce296b2eadc/0_723_3047_1827/master/3047.jpg?width=465&quality=45&auto=format&fit=max&dpr=2&s=9d672d83772bbcf8b17f4aae6cdb3d22",
                    "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F20%2F2020%2F10%2F26%2Fbad-bunny4-2000.jpg&q=85",
                    "https://static.billboard.com/files/media/ozuna-bad-bunny-latin-2020-billboard-u-1548-compressed.jpg"
                )
            )
        )
        return imagePosts
    }
}