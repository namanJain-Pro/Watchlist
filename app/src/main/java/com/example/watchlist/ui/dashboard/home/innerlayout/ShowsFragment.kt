package com.example.watchlist.ui.dashboard.home.innerlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentShowsBinding
import com.example.watchlist.datamodel.Category
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.datamodel.Layout
import com.example.watchlist.repository.Repository
import com.example.watchlist.ui.dashboard.DashboardFragmentDirections
import com.example.watchlist.ui.dashboard.common.bottomsheet.category.CategoryBottomSheet
import com.example.watchlist.ui.dashboard.common.bottomsheet.layout.LayoutBottomSheet
import com.example.watchlist.ui.dashboard.home.HomeRecyclerViewAdapter
import com.example.watchlist.ui.dashboard.home.HomeViewModel
import com.example.watchlist.ui.dashboard.home.HomeViewModelFactory
import com.example.watchlist.util.Constants.Companion.GRIDLAYOUT
import com.example.watchlist.util.Constants.Companion.LINEARLAYOUT
import com.example.watchlist.util.Constants.Companion.MOST_POPULAR_SHOWS
import com.example.watchlist.util.Constants.Companion.TOP_250_SHOW

class ShowsFragment : Fragment(R.layout.fragment_shows),
    HomeRecyclerViewAdapter.OnClickListener,
    CategoryBottomSheet.BottomSheetClickListener,
    LayoutBottomSheet.BottomSheetLayoutListener {

    private lateinit var binding: FragmentShowsBinding
    private var showsList: List<ExploreItem> = listOf()
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowsBinding.bind(view)

        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.fetch(TOP_250_SHOW)

        adapter = HomeRecyclerViewAdapter(GRIDLAYOUT, this)
        val layoutManager = GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        binding.showsRecyclerview.adapter = adapter
        binding.showsRecyclerview.layoutManager = layoutManager

        viewModel.responseList.observe(viewLifecycleOwner, {
            changeVisibility(true)
            showsList = it
            adapter.submitList(showsList)
        })

        // Setting up bottom sheet for category
        val list = listOf(Category(TOP_250_SHOW, true), Category(MOST_POPULAR_SHOWS, false))
        val bottomSheet = CategoryBottomSheet(list, this)

        binding.showsCategoryBtn.text = list[0].name
        binding.showsCategoryBtn.setOnClickListener {
            bottomSheet.show(childFragmentManager, "CategoryBottomSheet")
        }

        // Setting up bottom sheet for layout
        val layoutList = listOf(
            Layout(resources.getDrawable(R.drawable.ic_baseline_gird), GRIDLAYOUT, true),
            Layout(resources.getDrawable(R.drawable.ic_baseline_classic), LINEARLAYOUT, false)
        )
        val layoutBottomSheet = LayoutBottomSheet(layoutList, this)

        binding.showsLayout.setImageDrawable(layoutList[0].icon)
        binding.showsLayout.setOnClickListener {
            layoutBottomSheet.show(childFragmentManager, "LayoutBottomSheet")
        }
    }

    private fun changeVisibility(bool: Boolean) {
        if (bool) {
            binding.progressBar.visibility = View.GONE
            binding.showsRecyclerview.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.VISIBLE
            binding.showsRecyclerview.visibility = View.GONE
        }
    }

    override fun onExploreItemClick(position: Int) {
        val item: ExploreItem = viewModel.responseList.value?.get(position)!!
        val action = DashboardFragmentDirections.actionDashboardFragmentToTitleFragment(item.id)
        findNavController().navigate(action)
    }

    override fun onClickListener(category: String) {
        binding.showsCategoryBtn.text = category
    }

    override fun onLayoutClickListener(str: String) {
        binding.showsRecyclerview.layoutManager = when (str) {
            GRIDLAYOUT -> {
                adapter = HomeRecyclerViewAdapter(GRIDLAYOUT, this)
                binding.showsLayout.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_gird))
                GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
            }

            LINEARLAYOUT -> {
                adapter = HomeRecyclerViewAdapter(LINEARLAYOUT, this)
                binding.showsLayout.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_classic))
                LinearLayoutManager(binding.root.context)
            }

            else -> {
                adapter = HomeRecyclerViewAdapter(GRIDLAYOUT, this)
                binding.showsLayout.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_gird))
                GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
            }
        }
        binding.showsRecyclerview.adapter = adapter
        adapter.submitList(showsList)
    }
}