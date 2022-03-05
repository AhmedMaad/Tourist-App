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
        places.add(PlaceModel(R.drawable.pyramids, "Pyramids of Giza", 29.9773, 31.1325))
        places.add(PlaceModel(R.drawable.moez, "El-Moez Street", 30.0511, 31.2615))
        places.add(
            PlaceModel(
                R.drawable.pharao,
                "National Museum of Egyptian Civilization",
                30.0083,
                31.2482
            )
        )
        places.add(PlaceModel(R.drawable.baron, "Baron Empain Castle", 30.0869, 31.3301))
        places.add(PlaceModel(R.drawable.gem, "Grand Egyptian Museum", 29.9940, 31.1196))

        val adapter = PlaceAdapter(this, places)
        val recyclerView: RecyclerView = findViewById(R.id.rv)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = object : PlaceAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val i = Intent(this@HomeActivity, PlaceActivity::class.java)
                i.putExtra("place", places[position])
                //put extra for parcelable
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