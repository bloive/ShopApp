package com.example.shopappp.repository.post

import com.example.shopappp.models.Post
import com.example.shopappp.network.PostService
import com.example.shopappp.network.Resource
import com.google.gson.Gson
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postService: PostService) : PostRepository {
    override suspend fun getPost(): Resource<List<Post>> {
        return try {
            val response = postService.getPosts()
            if (response.isSuccessful) {
                val body = response.body()!!
                Resource.success(body)
            } else {
                val errorModel = Gson().fromJson(response.errorBody()!!.string(), Error::class.java)
                Resource.error(errorModel.message.toString() , "error")
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString(), "error")
        }
    }
}