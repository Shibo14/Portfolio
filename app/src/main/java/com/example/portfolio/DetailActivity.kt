package com.example.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.portfolio.adapter.DetailImagesAdapter
import com.example.portfolio.data.models.ProjectModel
import com.example.portfolio.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailImagesAdapter: DetailImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val project = intent?.getSerializableExtra("project") as ProjectModel


        detailImagesAdapter = DetailImagesAdapter()
        binding.rv.adapter = detailImagesAdapter

        detailImagesAdapter.submitData(project.images.toImageList())
    }
}