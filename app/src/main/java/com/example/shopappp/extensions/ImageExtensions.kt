package com.example.shopappp.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shopappp.R

fun ImageView.setImageUrl(url: String) {
    if (!url.isNullOrEmpty())
        Glide.with(context).load(url).placeholder(R.drawable.ic_placeholder).into(this)
    else
        setImageResource(R.drawable.ic_placeholder)
}

fun ImageView.setImageUri(uri: Uri?) {
    if (uri != null)
        Glide.with(context).load(uri).placeholder(R.drawable.ic_placeholder).into(this)
    else
        setImageResource(R.drawable.ic_placeholder)
}