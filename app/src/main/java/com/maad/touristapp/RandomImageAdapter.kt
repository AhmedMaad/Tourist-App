package com.maad.touristapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RandomImageAdapter(val activity: Activity, val images: ArrayList<String>) :
    RecyclerView.Adapter<RandomImageAdapter.ImageVH>() {

    class ImageVH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.random_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val view = activity.layoutInflater.inflate(R.layout.random_list_item, parent, false)
        return ImageVH(view)
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        Glide
            .with(activity)
            .load(images[position])
            .into(holder.image);
    }

    override fun getItemCount() = images.size
}