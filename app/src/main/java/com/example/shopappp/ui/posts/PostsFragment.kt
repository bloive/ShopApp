package com.example.shopappp.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shopappp.BaseFragment
import com.example.shopappp.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: PostsViewModel by viewModels()

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        TODO("Not yet implemented")
    }

}