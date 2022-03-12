package com.maad.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        title = "Home"

        val places = arrayListOf<PlaceModel>()

        val pyramidsDetails = arrayListOf<PlaceDetails>()
        pyramidsDetails.add(
            PlaceDetails(
                R.drawable.khufu,
                "Khufu Pyramid",
                "08:00 am - 04:00 pm",
                25.49,
                R.string.khufu_details
            )
        )
        pyramidsDetails.add(
            PlaceDetails(
                R.drawable.khafre,
                "Khafre Pyramid",
                "08:00 am - 04:00 pm",
                6.37,
                R.string.khafre_details
            )
        )
        pyramidsDetails.add(
            PlaceDetails(
                R.drawable.sound_light,
                "Sound & Light Show",
                "7 pm & 8 pm",
                20.0,
                R.string.light_sound_details
            )
        )
        pyramidsDetails.add(
            PlaceDetails(
                R.drawable.sphinx,
                "Sphinx",
                "08:00 am - 04:00 pm",
                0.0,
                R.string.sphinx_details
            )
        )

        places.add(
            PlaceModel(
                R.drawable.pyramids,
                "Pyramids of Giza",
                29.9773,
                31.1325,
                pyramidsDetails
            )
        )

        val moezDetails = arrayListOf<PlaceDetails>()
        moezDetails.add(
            PlaceDetails(
                R.drawable.khan_el_khalili,
                "Khan el-Khalili",
                "24/7",
                0.0,
                R.string.khan_el_khalili_details
            )
        )
        moezDetails.add(
            PlaceDetails(
                R.drawable.hakim_mosque,
                "Al Hakim Mosque",
                "09:00 am - 05:00 pm",
                0.0,
                R.string.al_hakim_mosque_details
            )
        )
        moezDetails.add(
            PlaceDetails(
                R.drawable.beit_el_seheimy,
                "Beit El Seheimy",
                "09:00 am - 05:00 pm",
                2.23,
                R.string.beit_el_seheimy_details
            )
        )

        places.add(PlaceModel(R.drawable.moez, "El-Moez Street", 30.0511, 31.2615, moezDetails))

        places.add(
            PlaceModel(
                R.drawable.pharao,
                "National Museum of Egyptian Civilization",
                30.0083,
                31.2482,
                arrayListOf()
            )
        )

        val baronDetails = arrayListOf<PlaceDetails>()
        baronDetails.add(
            PlaceDetails(
                R.drawable.baron_garden,
                "Palace Garden",
                "09:00 am - 06:00 pm",
                6.38,
                R.string.baron_palace_details
            )
        )
        baronDetails.add(
            PlaceDetails(
                R.drawable.baron_outside,
                "Palace (Outside View)",
                "09:00 am - 06:00 pm",
                6.38,
                R.string.baron_palace_details
            )
        )
        baronDetails.add(
            PlaceDetails(
                R.drawable.baron_roof,
                "Roof of the Palace",
                "09:00 am - 06:00 pm",
                6.38,
                R.string.baron_palace_details
            )
        )
        baronDetails.add(
            PlaceDetails(
                R.drawable.baron_stairs,
                "Inner Stairs",
                "09:00 am - 06:00 pm",
                6.38,
                R.string.baron_palace_details
            )
        )

        places.add(
            PlaceModel(
                R.drawable.baron,
                "Baron Empain Castle",
                30.0869,
                31.3301,
                baronDetails
            )
        )


        places.add(
            PlaceModel(
                R.drawable.gem,
                "Grand Egyptian Museum",
                29.9940,
                31.1196,
                arrayListOf()
            )
        )

        val adapter = PlaceAdapter(this, places)
        val recyclerView: RecyclerView = findViewById(R.id.rv)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = object : PlaceAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val i = Intent(this@HomeActivity, PlaceActivity::class.java)
                i.putExtra("place", places[position])
                startActivity(i)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.item_logout -> {
                Firebase.auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}