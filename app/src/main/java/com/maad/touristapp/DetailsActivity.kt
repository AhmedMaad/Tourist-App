package com.maad.touristapp

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.maad.touristapp.databinding.ActivityDetailsBinding

class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var details: PlaceDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        details = intent.getParcelableExtra("details")!!

        binding.attractionIv.setImageResource(details.picture)
        binding.bgIv.setImageResource(details.picture)
        binding.collapsingToolbarLayout.title = details.name
        binding.aboutTv.text = resources.getString(details.about)
        binding.workingHoursTv.text = details.workingHours
        binding.priceDataTv.text = details.ticketPrice.toString()

    }

}