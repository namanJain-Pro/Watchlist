package com.example.watchlist.ui.dashboard.common.bottomsheet.layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watchlist.R
import com.example.watchlist.datamodel.Layout

class LayoutRecyclerAdapter(
    private val list: List<Layout>,
    private val listener: OnLayoutItemListener
) : RecyclerView.Adapter<LayoutRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val icon: ImageView = view.findViewById(R.id.layout_icon)
        val name: TextView = view.findViewById(R.id.layout_name)
        val check: ImageView = view.findViewById(R.id.layout_check)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            for (item in list) {
                item.check = false
            }
            list[adapterPosition].check = true
            listener.onClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].apply {
            holder.icon.setImageDrawable(this.icon)
            holder.name.text = this.name

            if (this.check) {
                holder.check.visibility = View.VISIBLE
            } else {
                holder.check.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = list.size

    interface OnLayoutItemListener {
        fun onClickListener(position: Int)
    }
}