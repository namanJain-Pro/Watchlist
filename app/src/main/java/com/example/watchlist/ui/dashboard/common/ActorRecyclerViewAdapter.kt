package com.example.watchlist.ui.dashboard.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.watchlist.R
import com.example.watchlist.datamodel.Actor

class ActorRecyclerViewAdapter(private val list: List<Actor>) : RecyclerView.Adapter<ActorRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.actor_image)
        val name: TextView = view.findViewById(R.id.actor_name)
        val role: TextView = view.findViewById(R.id.actor_role)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_actor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].apply {
            holder.image.load(this.imageURL) {
                this.crossfade(true)
            }
            holder.name.text = this.name
            holder.role.text = "as ${this.asCharacter}"
        }
    }

    override fun getItemCount() = list.size

}