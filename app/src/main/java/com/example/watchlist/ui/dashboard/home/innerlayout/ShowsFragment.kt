package com.example.watchlist.ui.dashboard.home.innerlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentShowsBinding

class ShowsFragment : Fragment(R.layout.fragment_shows) {

    private lateinit var binding : FragmentShowsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowsBinding.bind(view)
    }

}