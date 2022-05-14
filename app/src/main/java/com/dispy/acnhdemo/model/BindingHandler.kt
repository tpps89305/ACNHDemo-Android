package com.dispy.acnhdemo.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.dispy.acnhdemo.view.custom.TimeScaleView

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

    @BindingAdapter("availableTimes")
    @JvmStatic
    fun bindingAvailableTimes(timeScaleView: TimeScaleView, array: List<Int>) {
        timeScaleView.setAvailableTimes(array)
    }

    @BindingAdapter("resourceString")
    @JvmStatic
    fun bindingResourceString(imageView: ImageView, resourceName: String) {
        imageView.load(
            ResourceHandler.getResourceId(
                context = imageView.context,
                resourceName = resourceName
            )
        ) {
            transformations(CircleCropTransformation())
        }
    }

}