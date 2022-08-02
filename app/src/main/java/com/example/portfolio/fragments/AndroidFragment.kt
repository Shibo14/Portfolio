package com.example.portfolio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.portfolio.adapter.ProjectAdapter
import com.example.portfolio.data.FirebaseDataReceiver
import com.example.portfolio.databinding.AndroidFragmentBinding

/**
 * @Author: Temur
 * @Date: 01/08/2022
 */

class AndroidFragment : Fragment() {

    private lateinit var binding: AndroidFragmentBinding
    private lateinit var adapter: ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AndroidFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProjectAdapter()
        binding.rvAndroid.adapter = adapter

        FirebaseDataReceiver().getProjects {
            adapter.submitData(it)
        }
    }
}