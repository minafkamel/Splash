package com.helloclue.androidassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.helloclue.androidassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPhotos()
        setupViews()
    }

    private fun setupViews() {
        binding.buttonAddPhotos.setOnClickListener {
            // Handle Click
        }
    }

    private fun getPhotos() {
        // Load photos from API
    }
}
