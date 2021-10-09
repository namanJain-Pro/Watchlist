package com.example.watchlist.ui.dashboard.collection.innerlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentWatchlistBinding

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private lateinit var binding : FragmentWatchlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchlistBinding.bind(view)
    }

}