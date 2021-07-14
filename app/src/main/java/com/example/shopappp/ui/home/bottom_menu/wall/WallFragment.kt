package com.example.shopappp.ui.home.bottom_menu.wall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopappp.BaseFragment
import com.example.shopappp.adapters.PostsAdapter
import com.example.shopappp.databinding.FragmentWallBinding
import com.example.shopappp.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallFragment : BaseFragment<FragmentWallBinding>(FragmentWallBinding::inflate) {
    private lateinit var postsAdapter: PostsAdapter
    private val viewModel: WallViewModel by viewModels()
    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        observes()
        initPosts()
        setListeners()
    }

    private fun initPosts() {
        viewModel.getPost()
        postsAdapter = PostsAdapter()
        with(binding!!) {
            recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
            recyclerPosts.adapter = postsAdapter
        }
    }

    private fun setListeners() {
        binding!!.swipeRefresh.setOnRefreshListener {
            viewModel.getPost()
        }
    }

    private fun observes() {
        viewModel.liveData.observe(viewLifecycleOwner, {
            binding!!.swipeRefresh.isRefreshing = it.loading
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> postsAdapter.setData(it1.toMutableList())}
                }
            }
        })

    }
}