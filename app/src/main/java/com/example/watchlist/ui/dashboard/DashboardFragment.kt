package com.example.watchlist.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.innerFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavBar.setupWithNavController(navController)
    }

}