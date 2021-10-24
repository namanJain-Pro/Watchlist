package com.example.watchlist.ui.dashboard.home.innerlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentMoviesBinding
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.ui.dashboard.home.HomeRecyclerViewAdapter
import com.example.watchlist.ui.dashboard.home.HomeViewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

//        val repository = Repository()
//        val viewModelFactory = HomeViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        val adapter = HomeRecyclerViewAdapter()
        val layoutManager = GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        binding.moviesRecyclerview.adapter = adapter
        binding.moviesRecyclerview.layoutManager = layoutManager

        val list = listOf<ExploreItem>(
            ExploreItem(
                "123e",
                "The Shawshank Redemption",
                "https://imdb-api.com/images/original/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6716_AL_.jpg",
                9.2f),
            ExploreItem(
                "123re",
                "The Godfather",
                "https://imdb-api.com/images/original/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.7015_AL_.jpg",
                9.1f
            )
        )

        adapter.submitList(list)
//        viewModel.topMovies.observe(viewLifecycleOwner, {
////            adapter.submitList(it)
//            Log.d("testing", "onViewCreated: ${it[0]}")
//        })

    }
}