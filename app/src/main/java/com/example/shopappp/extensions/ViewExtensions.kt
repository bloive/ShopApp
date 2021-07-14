package com.example.shopappp.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showIf(isValid: Boolean) {
    visibility = if (isValid)
        View.VISIBLE
    else
        View.INVISIBLE
}

fun View.hideIf(isValid: Boolean) {
    visibility = if (!isValid)
        View.VISIBLE
    else
        View.INVISIBLE
}