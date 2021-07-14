package com.example.shopappp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shopappp.R

fun ImageView.setImageUrl(url: String) {
    if (!url.isNullOrEmpty())
        Glide.with(context).load(url).placeholder(R.drawable.ic_placeholder).into(this)
    else
        setImageResource(R.drawable.ic_placeholder)
}