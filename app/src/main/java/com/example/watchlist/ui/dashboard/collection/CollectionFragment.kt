package com.example.watchlist.ui.dashboard.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentCollectionBinding
import com.example.watchlist.ui.dashboard.collection.innerlayout.WatchedFragment
import com.example.watchlist.ui.dashboard.collection.innerlayout.WatchlistFragment
import com.google.android.material.tabs.TabLayoutMediator

class CollectionFragment : Fragment(R.layout.fragment_collection) {

    private lateinit var binding : FragmentCollectionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCollectionBinding.bind(view)

        val fragmentList = listOf(
            WatchlistFragment(),
            WatchedFragment()
        )

        val adapter = CollectionViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.collectionViewPager.adapter = adapter
        TabLayoutMediator(binding.collectionTabLayout, binding.collectionViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Watchlist"
                1 -> tab.text = "Watched"
            }
        }.attach()
    }
}