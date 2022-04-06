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
            if (email.isEmpty() || password.isEmpty() || confirmPass.isEmpty())
                Toast.makeText(this, "Missing Required Fields", Toast.LENGTH_SHORT).show();
            else if (password != confirmPass)
                Toast.makeText(this, "Passwords should be the same", Toast.LENGTH_SHORT).show()
            else {
                binding.btnRegister.isEnabled = false
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful)
                            verifyEmail()
                        else {
                            binding.btnRegister.isEnabled = true
                            Toast.makeText(
                                this,
                                "${task.exception?.localizedMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            }

        }

        binding.tvRegistered.setOnClickListener { openLogin() }

    }

    private fun verifyEmail() {
        val user = Firebase.auth.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.btnRegister.isEnabled = true
                    Snackbar.make(
                        binding.parent, "Check your email", BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                    Handler(Looper.getMainLooper()).postDelayed({ openLogin() }, 2500)
                }

            }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}