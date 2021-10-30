package com.example.watchlist.ui.dashboard.home.innerlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentMoviesBinding
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.repository.Repository
import com.example.watchlist.ui.dashboard.DashboardFragmentDirections
import com.example.watchlist.ui.dashboard.home.HomeRecyclerViewAdapter
import com.example.watchlist.ui.dashboard.home.HomeViewModel
import com.example.watchlist.ui.dashboard.home.HomeViewModelFactory

class MoviesFragment : Fragment(R.layout.fragment_movies), HomeRecyclerViewAdapter.OnClickListener {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.fetchMovies()

        val adapter = HomeRecyclerViewAdapter(binding.root.context, this)
        val layoutManager = GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        binding.moviesRecyclerview.adapter = adapter
        binding.moviesRecyclerview.layoutManager = layoutManager

        viewModel.topMovies.observe(viewLifecycleOwner, {
            changeVisibility()
            adapter.submitList(it)
        })

    }

    private fun changeVisibility() {
        binding.progressBar.visibility = View.GONE
        binding.moviesRecyclerview.visibility = View.VISIBLE
    }

    override fun onExploreItemClick(position: Int) {
        val item: ExploreItem = viewModel.topMovies.value?.get(position)!!
        val action = DashboardFragmentDirections.actionDashboardFragmentToTitleFragment(item.id)
        findNavController().navigate(action)
    }
}