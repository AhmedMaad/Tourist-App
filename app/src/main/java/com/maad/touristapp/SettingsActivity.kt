package com.maad.touristapp

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maad.touristapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Settings"

        binding.changePasswordContainer.setOnClickListener {
            if (binding.writePasswordContainer.visibility == View.GONE)
                showDesign(View.VISIBLE, R.drawable.ic_up, R.anim.slide_down)
            else
                showDesign(View.GONE, R.drawable.ic_down, R.anim.slide_up)
        }

    binding.btnUpdate.setOnClickListener {
        val newPassword = binding.etUpdatedPassword.text.toString()
        if (newPassword.length < 6)
            Toast.makeText(this, "Passwords should be > 6 letters", Toast.LENGTH_LONG).show()
        else {
            val user = Firebase.auth.currentUser
            user!!.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                        Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show()
                }
        }
    }

    }

    private fun showDesign(visibility: Int, @DrawableRes image: Int, @AnimRes slideAnim: Int) {
        binding.writePasswordContainer.visibility = visibility
        binding.arrowIv.setImageResource(image)
        binding.writePasswordContainer.startAnimation(AnimationUtils.loadAnimation(this, slideAnim))
    }

}