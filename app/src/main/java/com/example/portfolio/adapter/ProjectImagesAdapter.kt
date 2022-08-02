package com.example.portfolio.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.OnClick
import com.example.portfolio.R
import com.example.portfolio.databinding.ImageItemBinding
import com.example.portfolio.databinding.TypeItemBinding
import java.util.*

/**
 * @Author: Temur
 * @Date: 01/08/2022
 */

data class ImageModel(
    val uri: Uri?,
    val isImage: Boolean = true,
    @DrawableRes val addImage: Int = R.drawable.ic_baseline_add_24,
)

class ProjectImagesAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val currentList = ArrayList<ImageModel>()

    init {
        currentList.add(ImageModel(null, isImage = false))
    }

    var selectedType = ""

    @RequiresApi(Build.VERSION_CODES.N)
    fun submitData(image: ImageModel) {
        currentList.add(image)
        currentList.removeIf { !it.isImage }
        currentList.add(ImageModel(null, isImage = false))
        notifyDataSetChanged()
    }

    var onClick: OnClick<Unit>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhImages(ImageItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            onClick)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VhImages).bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}

private class VhImages(private val binding: ImageItemBinding, private val onClick: OnClick<Unit>?) :
    RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(image: ImageModel) {
        if (image.isImage) {
            binding.icon.setImageURI(image.uri)
        } else {
            binding.icon.setImageResource(image.addImage)
        }

        binding.icon.setOnClickListener {
            if (!image.isImage) {
                onClick?.onClick(Unit)
            }
        }
    }
}
