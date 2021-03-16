package com.rodriguez.giomar.el_meneo.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodriguez.giomar.el_meneo.R
import com.rodriguez.giomar.el_meneo.databinding.FragmentVideoListScreenBinding

class VideoListScreenFragment : Fragment() {
    private var _binding: FragmentVideoListScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoListScreenBinding.inflate(inflater, container, false)




        return binding.root
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}