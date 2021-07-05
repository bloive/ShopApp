package com.example.shopappp.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setMultiColors(texts: List<String>, colors: List<Int>) {
    val spannable = SpannableString(texts.joinToString())
    var startIndex = 0
    for (i in texts.indices) {
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context,colors[i])),
            startIndex, startIndex + texts[i].length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        startIndex += texts[i].length
    }

    text = spannable
}