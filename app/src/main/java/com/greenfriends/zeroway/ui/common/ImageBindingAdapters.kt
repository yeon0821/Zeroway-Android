package com.greenfriends.zeroway.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.greenfriends.zeroway.GlideApp
import com.greenfriends.zeroway.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("userImageUrl")
fun loadUserImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    } else {
        GlideApp.with(view)
            .load(R.drawable.ic_profile)
            .into(view)
    }
}