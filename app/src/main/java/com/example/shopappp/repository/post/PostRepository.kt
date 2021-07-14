package com.example.shopappp.repository.post

import com.example.shopappp.models.Post
import com.example.shopappp.network.Resource

interface PostRepository {
    suspend fun getPost():Resource<List<Post>>
}