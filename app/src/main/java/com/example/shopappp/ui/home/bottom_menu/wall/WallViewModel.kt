package com.example.shopappp.ui.home.bottom_menu.wall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopappp.models.Post
import com.example.shopappp.network.Resource
import com.example.shopappp.repository.post.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class WallViewModel @Inject constructor(private val postRepo: PostRepositoryImpl) : ViewModel() {
    private val _postsLiveData = MutableLiveData<Resource<List<Post>>>()
    val postsLiveData: LiveData<Resource<List<Post>>> = _postsLiveData

    fun getPost() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _postsLiveData.postValue(Resource.loading(true))
                getPosts()
            }
        }
    }

    private suspend fun getPosts() {
        _postsLiveData.postValue(postRepo.getPost())
    }
}