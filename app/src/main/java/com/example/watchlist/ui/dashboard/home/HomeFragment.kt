package com.example.watchlist.ui.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentHomeBinding
import com.example.watchlist.ui.dashboard.home.innerlayout.MoviesFragment
import com.example.watchlist.ui.dashboard.home.innerlayout.ShowsFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val fragmentList = listOf(
            MoviesFragment(),
            ShowsFragment()
        )

        val adapter = HomeViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.homeViewPager.adapter = adapter
        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Shows"
            }
        }.attach()
    }
}