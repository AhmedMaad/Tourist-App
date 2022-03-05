package com.maad.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maad.touristapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.visibility = View.VISIBLE
            binding.root.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        }, 500)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        if (auth.currentUser!!.isEmailVerified) {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        } else
                            Snackbar
                                .make(
                                    binding.root,
                                    "Email is not verified, Check your email.",
                                    BaseTransientBottomBar.LENGTH_LONG
                                )
                                .show()
                    } else
                        Toast.makeText(
                            this,
                            "${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                }
        }

        binding.tvForgotPassword.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isEmpty())
                Toast.makeText(this, "Please write your email", Toast.LENGTH_SHORT).show()
            else
                AlertDialog
                    .Builder(this)
                    .setTitle("Confirm Email")
                    .setIcon(R.drawable.ic_email)
                    .setMessage("is this your correct email? $email")
                    .setPositiveButton("yes") { _, _ ->
                        Firebase.auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful)
                                    Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .setNegativeButton("no") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
        }

        binding.tvNotRegistered.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }


}