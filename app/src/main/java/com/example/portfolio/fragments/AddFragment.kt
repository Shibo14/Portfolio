package com.example.portfolio.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.portfolio.*
import com.example.portfolio.adapter.ProjectImagesAdapter
import com.example.portfolio.adapter.ProjectTypeAdapter
import com.example.portfolio.databinding.AddLayoutBinding

/**
 * @Author: Temur
 * @Date: 01/08/2022
 */

class AddFragment : Fragment() {

    private lateinit var binding: AddLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AddLayoutBinding.inflate(inflater)
        return binding.root
    }
}