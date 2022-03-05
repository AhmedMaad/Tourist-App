package com.maad.touristapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceOptionsAdapter(val activity: Activity,val options: ArrayList<OptionsModel>
, val onOptionsItemClickListener: OnOptionsItemClickListener):
RecyclerView.Adapter<PlaceOptionsAdapter.OptionsVH>(){

   // lateinit var onOptionsItemClickListener: OnOptionsItemClickListener

    interface OnOptionsItemClickListener{
        fun onOptionsItemClick(position: Int)
    }

    class OptionsVH(view: View, onOptionsItemClickListener: OnOptionsItemClickListener): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.place_option_iv)
        val text: TextView = view.findViewById(R.id.place_option_tv)
        init {
            view.setOnClickListener {
                onOptionsItemClickListener.onOptionsItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceOptionsAdapter.OptionsVH {
        val view = activity.layoutInflater.inflate(R.layout.place_options_list_item, parent, false)
        return OptionsVH(view, onOptionsItemClickListener)
    }

    override fun onBindViewHolder(holder: PlaceOptionsAdapter.OptionsVH, position: Int) {
        holder.image.setImageResource(options[position].picture)
        holder.text.text = options[position].name
    }

    override fun getItemCount() = options.size
}