package com.dispy.acnhdemo.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingHandler {

    @BindingAdapter("loadUrl")
    @JvmStatic
    fun bindingImage(imageView: ImageView, url: String?) {
        imageView.load(url)
    }
}