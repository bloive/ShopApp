package com.example.shopappp.models

import com.google.gson.annotations.SerializedName

data class Post(
    val id: Int?,
    val owner: Int?,
    val title: String?,
    val description: String?,
    val category_id: Int?,
    val urls: List<ImageItem>?,
    val tags: String?,
    val price: Float?,
    @SerializedName("price_type")
    val priceType: String?
) {
    data class ImageItem(val url: String)
}