package com.example.portfolio

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.portfolio.adapter.DetailImagesAdapter
import com.example.portfolio.data.models.ProjectModel
import com.example.portfolio.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailImagesAdapter: DetailImagesAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        val project = intent?.getSerializableExtra("project") as ProjectModel
        binding.descTv.text = project.description
        binding.textView.text = project.title

        detailImagesAdapter = DetailImagesAdapter()
        binding.rv.adapter = detailImagesAdapter

        detailImagesAdapter.submitData(project.images.toImageList())
    }
}