package com.example.portfolio.data

import android.util.Log
import com.example.portfolio.data.models.ProjectModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.sql.DriverManager.println
import java.util.*

/**
 * @Author: Temur
 * @Date: 02/08/2022
 */

class FirebaseDataReceiver {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val ref = firebaseDatabase.getReference("projects")

    fun getProjects(onSuccess: (List<ProjectModel>) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val projects = ArrayList<ProjectModel>()
                for (data in snapshot.children) {
                    val post = data.getValue<ProjectModel>()
                    Log.d("res", "Re data = ${post.toString()}")
                    projects.add(post ?: ProjectModel())
                }
                onSuccess(projects)
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })
    }
}