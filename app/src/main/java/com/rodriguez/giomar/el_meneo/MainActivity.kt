package com.rodriguez.giomar.el_meneo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodriguez.giomar.el_meneo.repository.YoutubeVideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}