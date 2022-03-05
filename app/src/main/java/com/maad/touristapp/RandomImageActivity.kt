package com.maad.touristapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_image)

        val rv: RecyclerView = findViewById(R.id.random_recycler_view)
        val progress: ProgressBar = findViewById(R.id.progress)

        val place = intent.getParcelableExtra<PlaceModel>("place")
        title = place?.name

        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val callable = retrofit.create(FlickerCallable::class.java)
        callable.getRandomPics(place?.lat, place?.lon, (1..5).random()).enqueue(object : Callback<RandomImageModel> {
            override fun onResponse(
                call: Call<RandomImageModel>,
                response: Response<RandomImageModel>
            ) {
                progress.visibility = View.GONE
                val adapter = RandomImageAdapter(
                    this@RandomImageActivity, response.body()?.photos?.photo
                )
                rv.adapter = adapter
            }

            override fun onFailure(call: Call<RandomImageModel>, t: Throwable) {
                progress.visibility = View.GONE
                Toast.makeText(this@RandomImageActivity, "Try Again Later", Toast.LENGTH_SHORT).show();
            }
        })

    }

}