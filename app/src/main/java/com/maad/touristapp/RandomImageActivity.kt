package com.maad.touristapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class RandomImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_image)

        val rv: RecyclerView = findViewById(R.id.random_recycler_view)
        //get data from flicker api

    }

}