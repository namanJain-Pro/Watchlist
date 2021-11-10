package com.example.watchlist.ui.dashboard.common.Title

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentTitileBinding
import com.example.watchlist.datamodel.Actor
import com.example.watchlist.datamodel.CommonEntity
import com.example.watchlist.datamodel.Title
import com.example.watchlist.repository.Repository
import com.example.watchlist.ui.dashboard.common.CommonViewModel
import com.example.watchlist.ui.dashboard.common.CommonViewModelFactory

private const val TAG = "TitleFragment"

class TitleFragment : Fragment(R.layout.fragment_titile) {

    private lateinit var binding: FragmentTitileBinding
    private lateinit var viewModel: CommonViewModel
    private val args: TitleFragmentArgs by navArgs()

    // Show more button flag
    private var showMoreFlag = false

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

        binding.plotShowMoreBtn.setOnClickListener {
            if (!showMoreFlag) {
                showMoreFlag = true
                binding.plotShowMoreBtn.text = "less"
                binding.plotShowMoreBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_less, 0, 0, 0)
                binding.plotTitle.ellipsize = null
                binding.plotTitle.maxLines = Int.MAX_VALUE
            } else {
                showMoreFlag = false
                binding.plotShowMoreBtn.text = "more"
                binding.plotShowMoreBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_more, 0, 0, 0)
                binding.plotTitle.ellipsize = TextUtils.TruncateAt.END
                binding.plotTitle.maxLines = 3
            }
        }
    }

    private fun loadViews(title: Title) {

        binding.imageTitle.load(title.imageURL) {
            this.crossfade(true)
        }
        binding.movieTitleTitle.text = title.title
        binding.yearTitle.text = "${title.year}"
        binding.runtimeTitle.text = title.runtime
        binding.ratingTitle.text = "${title.imDbRating}"
        binding.releaseDateTitle.text = title.releaseDate
        binding.genresTitle.text = title.genres
        binding.plotTitle.text = title.plot

        if (title.directorList.isNotEmpty()) {
            binding.directorTitle.text = listToText(title.directorList)
        } else {
            binding.directorHeader.visibility = View.GONE
            binding.directorTitle.visibility = View.GONE
            binding.separator2.visibility = View.GONE
        }

        if (title.starList.isNotEmpty()) {
            binding.starsTitle.text = listToText(title.starList)
        } else {
            binding.starsHeader.visibility = View.GONE
            binding.starsTitle.visibility = View.GONE
            binding.separator3.visibility = View.GONE
        }

        setUpActorRecyclerView(title.actorList)

        //setting up button
        binding.plotShowMoreBtn.text = "more"
        binding.plotShowMoreBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_more, 0, 0, 0)

    }

    private fun setUpActorRecyclerView(actorList: List<Actor>) {

        val adapter = ActorRecyclerViewAdapter(actorList)
        val layoutManager = GridLayoutManager(binding.root.context, 3, RecyclerView.VERTICAL, false)
        binding.actorsTitle.adapter = adapter
        binding.actorsTitle.layoutManager = layoutManager
    }

    private fun listToText(list: List<CommonEntity>) : String{
        var text = ""
        var count = 0

        for (entity in list) {
            Log.d(TAG, "listToText: ${entity.name} $count")
            if (count == list.size - 1) {
                text += entity.name
                break
            }
            text += entity.name
            text += ", "
            count++
        }

        return text
    }
}