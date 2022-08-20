package com.greenfriends.zeroway.ui.communitypostdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfriends.zeroway.databinding.ItemCommunityPostCommentsBinding
import com.greenfriends.zeroway.model.CommunityPostDetailResponse

class CommunityPostDetailCommentsAdapter :
    ListAdapter<CommunityPostDetailResponse, CommunityPostDetailCommentsAdapter.CommunityPostDetailCommentsViewHolder>(
        CommunityPostDetailCommentsDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityPostDetailCommentsViewHolder {
        val binding = ItemCommunityPostCommentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommunityPostDetailCommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityPostDetailCommentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommunityPostDetailCommentsViewHolder(private val binding: ItemCommunityPostCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val communityPostDetailCommentAdapter = CommunityPostDetailCommentAdapter()

        fun bind(communityPostDetailResponse: CommunityPostDetailResponse) {
            binding.itemCommunityPostCommentsRv.adapter = communityPostDetailCommentAdapter
            communityPostDetailCommentAdapter.submitList(communityPostDetailResponse.communityPostDetailComments)
            binding.communityPostDetailResponse = communityPostDetailResponse
            binding.executePendingBindings()
        }
    }
}

class CommunityPostDetailCommentsDiffCallback :
    DiffUtil.ItemCallback<CommunityPostDetailResponse>() {

    override fun areItemsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(
        oldItem: CommunityPostDetailResponse,
        newItem: CommunityPostDetailResponse
    ): Boolean {
        return oldItem == newItem
    }
}