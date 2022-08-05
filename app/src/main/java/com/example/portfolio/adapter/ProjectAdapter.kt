package com.example.portfolio.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.OnClick
import com.example.portfolio.R
import com.example.portfolio.data.models.ProjectModel
import com.example.portfolio.databinding.AndroidItemBinding
import com.example.portfolio.databinding.TypeItemBinding
import com.example.portfolio.toImageList
import com.squareup.picasso.Picasso
import java.util.*

/**
 * @Author: Temur
 * @Date: 01/08/2022
 */

class ProjectAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val currentList = ArrayList<ProjectModel>()
    var selectedType = ""

    fun submitData(list: List<ProjectModel>) {
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    var onClick: OnClick<Type>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VhProject(AndroidItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            onClick)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VhProject).bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}

private class VhProject(
 private val binding: AndroidItemBinding,
 private val onClick: OnClick<Type>?,
) :
    RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(project: ProjectModel) {
        binding.descTv.text = project.description
        binding.titleTv.text = project.title
        Picasso.get().load(project.images.toImageList()[0]).into(binding.img)
    }
}
