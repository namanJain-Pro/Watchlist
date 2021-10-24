package com.example.watchlist.ui.dashboard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.datamodel.ExploreItem
import com.squareup.picasso.Picasso

class HomeRecyclerView() : RecyclerView.Adapter<HomeRecyclerView.ViewHolder>() {

    companion object val DIFF_CALLBACK: DiffUtil.ItemCallback<ExploreItem> = object : DiffUtil.ItemCallback<ExploreItem>() {
        override fun areItemsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil: AsyncListDiffer<ExploreItem> = AsyncListDiffer(this, DIFF_CALLBACK)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.title_explore)
        val image: ImageView = view.findViewById(R.id.image_explore)
        val rank: TextView = view.findViewById(R.id.rank_explore)
        val rating: TextView = view.findViewById(R.id.rating_explore)
        val btnAddToWatchlist: Button = view.findViewById(R.id.btn_add_to_watchlist)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_explore_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        diffUtil.currentList[position].apply {
            if (this != null) {
                holder.title.text = this.title
                holder.rank.text = "${this.rank}"
                holder.rating.text = "${this.imDbRating}"

                Picasso.get()
                    .load(this.imageURL)
                    .into(holder.image)
            }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun submitList(list: List<ExploreItem>) {
        diffUtil.submitList(list)
    }
}
