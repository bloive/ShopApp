package com.example.shopappp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopappp.databinding.ItemImageBinding
import com.example.shopappp.extensions.setImageUrl
import com.example.shopappp.models.Post

class ViewPagerAdapter(private val images: MutableList<Post.ImageItem>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.image.setImageUrl(images[adapterPosition].url)
        }
    }
}