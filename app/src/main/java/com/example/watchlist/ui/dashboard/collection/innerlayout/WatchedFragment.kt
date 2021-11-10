package com.example.watchlist.ui.dashboard.collection.innerlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentWatchedBinding

class WatchedFragment : Fragment(R.layout.fragment_watched) {

    private lateinit var binding : FragmentWatchedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchedBinding.bind(view)
    }
}