package com.maad.touristapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.maad.touristapp.databinding.ActivityPlaceBinding


class PlaceActivity : AppCompatActivity(), PlaceOptionsAdapter.OnOptionsItemClickListener,
    AttractionsAdapter.OnDetailsItemClickListener {

    private lateinit var binding: ActivityPlaceBinding
    private lateinit var place: PlaceModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        place = intent.getParcelableExtra("place")!!

        title = place.name

        val options = arrayListOf<OptionsModel>()
        options.add(OptionsModel(R.drawable.ic_hotel, "Nearby Hotel"))
        options.add(OptionsModel(R.drawable.ic_food, "Nearby Restaurants"))
        options.add(OptionsModel(R.drawable.ic_car, "Take a ride"))
        options.add(OptionsModel(R.drawable.ic_photo, "Random Pictures"))

        val placeOptionsAdapter = PlaceOptionsAdapter(this, options, this, place)
        binding.optionsRv.adapter = placeOptionsAdapter

        if (place.details.size == 0) {
            if (place.name == "Grand Egyptian Museum")
                binding.comingSoonLayout.root.visibility = View.VISIBLE
            else {
                //show the video for egyptian civilization
                binding.museumLayout.root.visibility = View.VISIBLE
                binding.museumLayout.videoview.setVideoURI("android.resource://$packageName/${R.raw.museum}".toUri())

                val videoController = MediaController(this)
                videoController.setMediaPlayer(binding.museumLayout.videoview)
                binding.museumLayout.videoview.setMediaController(videoController)
                binding.museumLayout.videoview.start()

                binding.museumLayout.fullscreenIv.setOnClickListener {
                    binding.museumLayout.videoview.pause()
                    val i = Intent(this, VideoActivity::class.java)
                    i.putExtra("currentTime", binding.museumLayout.videoview.currentPosition)
                    startActivityForResult(i, 500)
                }

            }
        } else {
            val attractionsAdapter = AttractionsAdapter(this, place.details, this)
            binding.attractionRv.adapter = attractionsAdapter
        }

    }

    override fun onDetailsItemClick(position: Int) {
        val i = Intent(this, DetailsActivity::class.java)
        i.putExtra("details", place.details[position])
        startActivity(i)
    }

    override fun onOptionsItemClick(position: Int, view: View) {
        when (position) {
            0 -> {
                //Nearby Hotels
                val gmmIntentUri = Uri.parse("geo:${place.lat},${place.lon}?q=hotels")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
            1 -> {
                //Nearby Restaurants
                val gmmIntentUri = Uri.parse("geo:${place.lat},${place.lon}?q=restaurants")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
            2 -> {
                //Uber Ride
                try {
                    val gmmIntentUri = Uri.parse(("geo:${place.lat},${place.lon}"))
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.`package` = "com.ubercab"
                    startActivity(mapIntent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(this, "Uber not found", Toast.LENGTH_LONG).show()
                    val uberLink = Uri.parse("market://details?id=com.ubercab")
                    startActivity(Intent(Intent.ACTION_VIEW, uberLink))
                }
            }
            3 -> {
                if (place.name == "Grand Egyptian Museum")
                    Snackbar
                        .make(binding.root, "Coming Soon", BaseTransientBottomBar.LENGTH_LONG)
                        .show()
                else {
                    val i = Intent(this, RandomImageActivity::class.java)
                    i.putExtra("place", place)
                    startActivity(i)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 500 && resultCode == RESULT_OK) {
            binding.museumLayout.videoview.seekTo(data!!.getIntExtra("currentTime", 0))
            binding.museumLayout.videoview.start()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}