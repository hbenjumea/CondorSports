package com.example.condorsports.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.condorsports.R
import com.example.condorsports.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_CondorSports)
        setContentView(binding.root)
    }


}