package com.maad.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maad.touristapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.visibility = View.VISIBLE
            binding.root.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        }, 500)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()
            if (password != confirmPass)
                Toast.makeText(this, "Password should be the same", Toast.LENGTH_SHORT).show()
            else
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful)
                            verifyEmail()
                        else
                            Toast.makeText(
                                this,
                                "${task.exception?.localizedMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
        }

        binding.tvRegistered.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun verifyEmail() {
        val user = Firebase.auth.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    Snackbar
                        .make(
                            binding.parent,
                            "Check your email",
                            BaseTransientBottomBar.LENGTH_LONG
                        )
                        .show()
            }
    }

}