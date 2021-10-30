package com.example.watchlist.ui.dashboard.common

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentTitileBinding
import com.example.watchlist.datamodel.Title
import com.example.watchlist.repository.Repository

private const val TAG = "TitleFragment"

class TitleFragment : Fragment(R.layout.fragment_titile) {

    private lateinit var binding: FragmentTitileBinding
    private lateinit var viewModel: CommonViewModel
    private val args: TitleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTitileBinding.bind(view)

        val repository = Repository()
        val viewModelFactory = CommonViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CommonViewModel::class.java]

        val id = args.id
        if (id != "-1") {
            viewModel.fetchTitle(id)
        } else {
            Log.d(TAG, "onViewCreated: id is invalid $id")
        }

        viewModel.title.observe(viewLifecycleOwner, {
            loadViews(it)
        })

        binding.toolbar.setNavigationOnClickListener {
            val action = TitleFragmentDirections.actionTitleFragmentToDashboardFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadViews(title: Title) {
        Glide.with(binding.root.context)
            .load(title.imageURL)
            .into(binding.imageTitle)

        binding.movieTitleTitle.text = title.title
        binding.yearTitle.text = "${title.year}"
        binding.runtimeTitle.text = title.runtime
        binding.ratingTitle.text = "${title.imDbRating}"
    }


}