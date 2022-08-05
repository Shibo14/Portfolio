package com.example.portfolio.data.models

/**
 * @Author: Temur
 * @Date: 02/08/2022
 */

data class ProjectModel(
    var id: String = "",
    val title: String,
    val description: String,
    val type: String,
    val images: String = "",
    val isAvailable: Boolean = false,
) {
    constructor() : this("", "", "", "")
}