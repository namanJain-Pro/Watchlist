package com.example.watchlist.ui.dashboard.common.bottomsheet.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.datamodel.Category

class CategoryRecyclerAdapter(
    private val list: List<Category>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val name: TextView = view.findViewById(R.id.category_name)
        val image: ImageView = view.findViewById(R.id.category_check)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            for (cat in list) {
                cat.check = false
            }
            list[adapterPosition].check = true
            listener.onClickListener(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        if (list[position].check) {
            holder.image.visibility = View.VISIBLE
        } else {
            holder.image.visibility = View.GONE
        }
    }

    override fun getItemCount() = list.size
}