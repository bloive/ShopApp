package com.example.shopappp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.shopappp.databinding.PostsItemBinding
import com.example.shopappp.extensions.hideIf
import com.example.shopappp.models.Post

class PostsAdapter() :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private val posts: MutableList<Post> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(PostsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(private val binding: PostsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Post
        private lateinit var imageAdapter: ViewPagerAdapter
        fun bind() {
            model = posts[adapterPosition]
            initImageAdapter()
            setData(posts)
        }

        private fun initImageAdapter() {
            imageAdapter = model.urls?.let { ViewPagerAdapter(it.toMutableList()) }!!
            val size: Int = model.urls?.size ?: 0
            with(binding) {
                viewPager.adapter = imageAdapter
                viewPager.hideIf(model.urls.isNullOrEmpty())
                btnRight.hideIf(size < 2)
                btnLeft.hideIf(size < 2)
                tvPage.hideIf(size < 2)
            }
            if (size >= 2)
                setCurrentPageIndex(1)
            binding.viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentPageIndex(position + 1)
                }
            })
        }

        private fun setData(posts: MutableList<Post>) {
            with(binding) {
                tvTitle.text = model.title
                tvDescription.text = model.description
                tvPrice.text = "$${model.price.toString()}"
            }
        }

        private fun setCurrentPageIndex(index: Int) {
            binding.tvPage.text = "$index/${model.urls?.size}"
        }
    }

    fun setData(posts: MutableList<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }
}