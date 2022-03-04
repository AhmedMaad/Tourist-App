package com.maad.touristapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(private val activity: Activity, private val places: ArrayList<PlaceModel>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceVH>() {

    lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    class PlaceVH(view: View, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
        val place: ImageView = view.findViewById(R.id.place_iv)
        val name: TextView = view.findViewById(R.id.tv_name)
        val bg: ImageView = view.findViewById(R.id.place_background_iv)
        init {
           view.setOnClickListener {
               onItemClickListener.onItemClick(adapterPosition)
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceVH {
        val view = activity.layoutInflater.inflate(R.layout.places_list_item, parent, false)
        return PlaceVH(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PlaceVH, position: Int) {
        holder.place.setImageResource(places[position].picture)
        holder.name.text = places[position].name
        holder.bg.setImageResource(places[position].picture)
    }

    override fun getItemCount() = places.size
}