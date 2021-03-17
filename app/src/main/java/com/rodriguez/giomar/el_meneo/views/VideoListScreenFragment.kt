package com.rodriguez.giomar.el_meneo.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.adapter.YoutubeVideoListAdapter
import com.rodriguez.giomar.el_meneo.databinding.FragmentVideoListScreenBinding
import com.rodriguez.giomar.el_meneo.viewModels.VideoListScreenFragmentViewModel

class VideoListScreenFragment : Fragment() {
    val TAG = "VideoListScreenFragment"
    private var _binding: FragmentVideoListScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var videoAdapter: YoutubeVideoListAdapter
    private lateinit var model: VideoListScreenFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoListScreenBinding.inflate(inflater, container, false)
        model = ViewModelProvider(this)[VideoListScreenFragmentViewModel::class.java]
        initializeRecyclerView()
        model.getVideos().observe(viewLifecycleOwner, Observer { videos ->
            videoAdapter.setVideos(videos)
            videoAdapter.notifyDataSetChanged()
        })
        model.getIsLoading().observe(viewLifecycleOwner, Observer {
            binding.pbIsLoading.visibility = View.GONE
        })


        return binding.root
    }

    private fun initializeRecyclerView() {
        binding.rvYoutubeVideoList.apply {
            layoutManager = LinearLayoutManager(context)
            videoAdapter = YoutubeVideoListAdapter(){ video ->
                val action = VideoListScreenFragmentDirections.actionVideoListScreenFragmentToYoutubeVideoPlayerFragment()
                findNavController().navigate(R.id.action_videoListScreenFragment_to_youtubeVideoPlayerFragment)
            }
            adapter = videoAdapter
        }
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}