package com.rodriguez.giomar.el_meneo.ui.postDetailsScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.databinding.FragmentInstagramOembedBinding
import com.rodriguez.giomar.el_meneo.sampleData.SampleData

class PostDetailsScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imagePost = SampleData.getImagePosts()
        val imagesUrl = imagePost[0].imagesUrl
        //val storage = FirebaseStorage.getInstance()
        return ComposeView(requireContext()).apply {
            val myHtmlString = """<iframe width="560" height="315" src="https://www.youtube.com/embed/T912v0mSyuY?controls=0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>"""
            setContent {
                InstagramPost(urlToRender = myHtmlString)
//                PostDetailsScreenComposable(imagesUrl){
//                    findNavController().navigateUp()
//                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InstagramPost(urlToRender: String) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadDataWithBaseURL("https://instagram.com", urlToRender, "text/html", "UTF-8", "")
            //loadUrl(urlToRender)
        }
    }, update = {
        it.loadDataWithBaseURL("https://instagram.com", urlToRender, "text/html", "UTF-8", "")
    })
}