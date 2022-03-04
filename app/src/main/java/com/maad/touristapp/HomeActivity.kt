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
        places.add(PlaceModel(R.drawable.pyramids, "Pyramids of Giza"))
        places.add(PlaceModel(R.drawable.moez, "El-Moez Street"))
        places.add(PlaceModel(R.drawable.pharao, "National Museum of Egyptian Civilization"))
        places.add(PlaceModel(R.drawable.baron, "Baron Castle"))
        places.add(PlaceModel(R.drawable.gem, "Grand Egyptian Museum"))

        val adapter = PlaceAdapter(this, places)
        val recyclerView: RecyclerView = findViewById(R.id.rv)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = object : PlaceAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val i = Intent(this@HomeActivity, PlaceActivity::class.java)
                i.putExtra("name", places[position].name)
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