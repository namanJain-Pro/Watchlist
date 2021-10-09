package com.example.watchlist.ui.dashboard.collection.innerlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentWatchedBinding

class WatchedFragment : Fragment(R.layout.fragment_watched) {

    private lateinit var binding : FragmentWatchedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchedBinding.bind(view)
    }
}