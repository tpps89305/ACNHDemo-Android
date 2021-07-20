package com.dispy.acnhdemo.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

object BindingHandler {

    @BindingAdapter("loadUrl")
    @JvmStatic
    fun bindingImage(imageView: ImageView, url: String?) {
        imageView.load(url)
    }

    @BindingAdapter("loadVillagerAvatarImageUrl")
    @JvmStatic
    fun bindingVillagerAvatarImage(imageView: ImageView, url: String?) {
        imageView.load(url) {
            transformations(RoundedCornersTransformation(50F))
        }
    }
}