package com.example.watchlist.ui.dashboard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.watchlist.R
import com.example.watchlist.datamodel.ExploreItem
import com.example.watchlist.util.Constants.Companion.GRIDLAYOUT
import com.example.watchlist.util.Constants.Companion.LINEARLAYOUT

class HomeRecyclerViewAdapter(
    private val layoutType: String,
    private val listener: OnClickListener
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    companion object val DIFF_CALLBACK: DiffUtil.ItemCallback<ExploreItem> = object : DiffUtil.ItemCallback<ExploreItem>() {
        override fun areItemsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil: AsyncListDiffer<ExploreItem> = AsyncListDiffer(this, DIFF_CALLBACK)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.title_explore)
        val image: ImageView = view.findViewById(R.id.image_explore)
        val rating: TextView = view.findViewById(R.id.rating_explore)
        val btnAddToWatchlist: Button = view.findViewById(R.id.btn_add_to_watchlist)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.explore_constraint_layout)

        init {
            constraintLayout.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onExploreItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutID = when (layoutType) {
            LINEARLAYOUT -> R.layout.layout_explore_item_classic
            GRIDLAYOUT -> R.layout.layout_explore_item_grid
            else -> R.layout.layout_explore_item_grid
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        diffUtil.currentList[position].apply {
            if (this != null) {
                holder.title.text = this.title
                holder.rating.text = "${this.imDbRating}"
                holder.image.load(this.imageURL) {
                    this.crossfade(true)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    fun submitList(list: List<ExploreItem>) {
        diffUtil.submitList(list)
    }

    interface OnClickListener {
        fun onExploreItemClick(position: Int)
    }
}
