package com.maad.touristapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttractionsAdapter(
    private val activity: Activity,
    private val details: ArrayList<PlaceDetails>,
    private val onDetailsItemClickListener: OnDetailsItemClickListener,
) :
    RecyclerView.Adapter<AttractionsAdapter.AttractionsVH>() {

    interface OnDetailsItemClickListener {
        fun onDetailsItemClick(position: Int)
    }

    class AttractionsVH(view: View, onDetailsItemClickListener: OnDetailsItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.attraction_title_tv)
        val image: ImageView = view.findViewById(R.id.attraction_iv)

        init {
            view.setOnClickListener {
                onDetailsItemClickListener.onDetailsItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsVH {
        val view = activity.layoutInflater.inflate(R.layout.attraction_list_item, parent, false)
        return AttractionsVH(view, onDetailsItemClickListener)
    }

    override fun onBindViewHolder(holder: AttractionsVH, position: Int) {
        holder.title.setText(details[position].name)
        holder.image.setImageResource(details[position].picture)
    }

    override fun getItemCount() = details.size
}