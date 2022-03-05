package com.maad.touristapp

import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class RandomImageAdapter(val activity: Activity, val images: ArrayList<Images>?) :
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
            .load(images?.get(position)?.url_m)
            .placeholder(R.drawable.loading)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(holder.image)
    }

    override fun getItemCount() = images?.size ?: 0
}