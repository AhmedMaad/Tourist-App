package com.maad.touristapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import androidx.core.net.toUri
import com.maad.touristapp.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoview.setVideoURI("android.resource://$packageName/${R.raw.museum}".toUri())

        val videoController = MediaController(this)
        videoController.setMediaPlayer(binding.videoview)
        binding.videoview.setMediaController(videoController)
        binding.videoview.seekTo(intent.getIntExtra("currentTime", 0))
        binding.videoview.start()

    }

    override fun onBackPressed() {
        val i = intent
        i.putExtra("currentTime", binding.videoview.currentPosition)
        setResult(RESULT_OK, i)
        finish()
    }

}