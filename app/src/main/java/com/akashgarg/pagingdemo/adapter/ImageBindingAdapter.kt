package com.akashgarg.pagingdemo.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by Akash Garg on 16-03-2019.
 */

object ImageBindingAdapter {
    @BindingAdapter("imageURL")
    @JvmStatic
    fun setImageURL(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }
}