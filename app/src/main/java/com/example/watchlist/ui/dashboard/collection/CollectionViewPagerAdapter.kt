package com.example.watchlist.ui.dashboard.collection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CollectionViewPagerAdapter(
    private val fragment_list: List<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = fragment_list.size

    override fun createFragment(position: Int) = fragment_list[position]
}