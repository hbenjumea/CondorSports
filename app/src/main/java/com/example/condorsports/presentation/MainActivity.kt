package com.example.condorsports.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.condorsports.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainFragment = MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.flBody.id,mainFragment).commit()
    }
}