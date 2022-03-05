package com.maad.touristapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PlaceOptionsAdapter(
    val activity: Activity,
    val options: ArrayList<OptionsModel>,
    val onOptionsItemClickListener: OnOptionsItemClickListener,
    val place: PlaceModel?
) :
    RecyclerView.Adapter<PlaceOptionsAdapter.OptionsVH>() {

    interface OnOptionsItemClickListener {
        fun onOptionsItemClick(position: Int, view: View)
    }

    class OptionsVH(view: View, onOptionsItemClickListener: OnOptionsItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.place_option_iv)
        val text: TextView = view.findViewById(R.id.place_option_tv)
        val parent: CardView = view.findViewById(R.id.parent)

        init {
            view.setOnClickListener {
                onOptionsItemClickListener.onOptionsItemClick(adapterPosition, view)
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
        if (place?.name == "Grand Egyptian Museum" && position == 3)
            holder.parent.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.darker_gray))
    }

    override fun getItemCount() = options.size
}