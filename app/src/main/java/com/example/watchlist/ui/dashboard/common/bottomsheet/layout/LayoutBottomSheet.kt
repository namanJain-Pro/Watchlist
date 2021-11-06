package com.example.watchlist.ui.dashboard.common.bottomsheet.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.datamodel.Layout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LayoutBottomSheet(
    private val list: List<Layout>,
    private val listener: BottomSheetLayoutListener
) : BottomSheetDialogFragment(), LayoutRecyclerAdapter.OnLayoutItemListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.layout_recycler_view)
        val adapter = LayoutRecyclerAdapter(list, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClickListener(position: Int) {
        listener.onLayoutClickListener(list[position].name)
        dismiss()
    }

    interface BottomSheetLayoutListener {
        fun onLayoutClickListener(str: String)
    }
}