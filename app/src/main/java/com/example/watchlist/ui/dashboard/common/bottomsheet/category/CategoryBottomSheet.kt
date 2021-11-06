package com.example.watchlist.ui.dashboard.common.bottomsheet.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.datamodel.Category
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategoryBottomSheet(
    private val list: List<Category>,
    private val listener: BottomSheetClickListener
) : BottomSheetDialogFragment(), CategoryRecyclerAdapter.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.category_recycler_view)
        val adapter = CategoryRecyclerAdapter(list, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClickListener(position: Int) {
        listener.onClickListener(list[position].name)
        dismiss()
    }

    interface BottomSheetClickListener {
        fun onClickListener(category: String)
    }
}