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
import androidx.recyclerview.widget.RecyclerView
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.adapter.YoutubeVideoListAdapter
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.el_meneo.databinding.FragmentVideoListScreenBinding
import com.rodriguez.giomar.el_meneo.ui.homeScreen.HomeScreen
import com.rodriguez.giomar.el_meneo.ui.homeScreen.HomeScreenDirections
import com.rodriguez.giomar.el_meneo.viewModels.VideoListScreenFragmentViewModel
import com.rodriguez.giomar.el_meneo.viewModels.shared.SharedYoutubeVideoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoListScreenFragment : Fragment() {
    val TAG = "VideoListScreenFragment"
    private var _binding: FragmentVideoListScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var videoAdapter: YoutubeVideoListAdapter
    private lateinit var model: VideoListScreenFragmentViewModel
    private lateinit var sharedModel: SharedYoutubeVideoViewModel
    private lateinit var mLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoListScreenBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity())[VideoListScreenFragmentViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedYoutubeVideoViewModel::class.java]
        mLayoutManager = LinearLayoutManager(context)
        initializeRecyclerView()
        model.videos.observe(viewLifecycleOwner, Observer { videos ->
            sharedModel.setRelatedVideos(videos)
            videoAdapter.setVideos(videos)
            videoAdapter.notifyDataSetChanged()
        })
        model.isLoading.observe(viewLifecycleOwner, Observer {
            binding.pbIsLoading.visibility = View.GONE
        })

        return binding.root
    }

    private fun initializeRecyclerView() {
        Log.d(TAG, "initializing recyclerView")
        binding.rvYoutubeVideoList.apply {

            layoutManager = mLayoutManager
            videoAdapter = YoutubeVideoListAdapter(){ selectedVideo ->
                sharedModel.loadInterstitialAd()

                val action = VideoListScreenFragmentDirections.actionVideoListScreenFragmentToYoutubeVideoPlayerFragment(selectedVideo)
                findNavController().navigate(action)
            }
            adapter = videoAdapter
            videoAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        //model.scrollPosition = mLayoutManager.findFirstVisibleItemPosition()
        //model.scrollOffset = binding.rvYoutubeVideoList.computeVerticalScrollOffset()
        Log.d(TAG, "pos: ${model.scrollPosition}")
        Log.d(TAG, "offset: ${model.scrollOffset}")
        _binding = null

        super.onDestroy()
    }
}