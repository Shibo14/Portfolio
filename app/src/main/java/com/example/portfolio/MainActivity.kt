package com.example.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.portfolio.adapter.FragmentController
import com.example.portfolio.databinding.ActivityMainBinding
import com.example.portfolio.fragments.AddFragment
import com.example.portfolio.fragments.AndroidFragment
import com.example.portfolio.fragments.WebFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        FragmentController(this,listOf(AndroidFragment(),AddFragment(),WebFragment()))

    }
}