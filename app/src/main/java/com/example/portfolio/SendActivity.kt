package com.example.portfolio

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.portfolio.adapter.ImageModel
import com.example.portfolio.adapter.ProjectImagesAdapter
import com.example.portfolio.adapter.ProjectTypeAdapter
import com.example.portfolio.adapter.Type
import com.example.portfolio.data.FirebaseDataSender
import com.example.portfolio.data.models.ProjectModel
import com.example.portfolio.databinding.ActivitySendBinding
import com.example.portfolio.databinding.AddLayoutBinding

class SendActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySendBinding
    private lateinit var adapter: ProjectTypeAdapter
    private lateinit var adapterImages: ProjectImagesAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ProjectTypeAdapter()
        adapterImages = ProjectImagesAdapter()
        binding.typesRv.adapter = adapter
        binding.imagesRv.adapter = adapterImages

        adapter.submitData(arrayListOf(
            Type(1, "android", R.drawable.ic_baseline_phone_android_24, false),
            Type(2, "web", R.drawable.ic_baseline_desktop_mac_24, false),
        ))

        adapter.onClick = OnClick { adapterType ->
            adapter.selectedType = adapterType?.title ?: ""
            adapter.submitData(adapter.currentList.map { it.copy(isSelected = it.id == adapterType?.id) })
        }

        binding.sendBtn.setOnClickListener {
            FirebaseDataSender().send(ProjectModel(title = binding.projectNameEdt.text.toString(),
                description = binding.projectDescEdt.text.toString(), type = adapter.selectedType))
        }

        adapterImages.onClick = OnClick {
            selectImageFromGalleryResult.launch("image/*")
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                adapterImages.submitData(ImageModel(uri))
                binding.counterTv.text = ("${adapterImages.currentList.size}/10")
//                val storage = FirebaseStorage.getInstance()
//                val ref = storage.getReference("project_images")
//                ref.putFile(uri).addOnSuccessListener {
//                    binding.sendBtn.text = it.uploadSessionUri.toString()
//                }
//                binding.type.icon.setImageURI(uri)
            }
        }
}