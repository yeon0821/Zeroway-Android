package com.greenfriends.zeroway.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("communityPostLikeCount")
fun communityPostLikeCount(view: TextView, likeCount: Int) {
    view.text = "$likeCount" + "개"
}

@BindingAdapter("communityPostCommentCount")
fun communityPostCommentCount(view: TextView, commentCount: Int) {
    view.text = "댓글" + "$commentCount" + "개 모두 보기"
}