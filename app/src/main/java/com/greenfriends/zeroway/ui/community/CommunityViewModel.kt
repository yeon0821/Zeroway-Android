package com.greenfriends.zeroway.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.model.CommunityPost
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
import com.greenfriends.zeroway.repository.community.CommunityRepository
import com.greenfriends.zeroway.ui.common.Event
import kotlinx.coroutines.launch

class CommunityViewModel(private val communityRepository: CommunityRepository) : ViewModel() {

    private val _sort = MutableLiveData<String>("createdAt")
    val sort: LiveData<String> = _sort

    private val _communityPosts = MutableLiveData<List<CommunityPost>>()
    val communityPosts: LiveData<List<CommunityPost>> = _communityPosts

    private val _communityPostDetailEvent = MutableLiveData<Event<Long>>()
    val communityPostDetailEvent: LiveData<Event<Long>> = _communityPostDetailEvent

    fun setSort(sort: String) {
        _sort.value = sort
    }

    fun getSort(): String? {
        return _sort.value
    }

    fun setCommunityPostDetailEvent(postId: Long) {
        _communityPostDetailEvent.value = Event(postId)
    }

    fun getPosts(accessToken: String, sort: String) {
        viewModelScope.launch {
            val response = communityRepository.getPosts(accessToken, sort)
            if (response.isSuccessful) {
                _communityPosts.value = response.body()!!.communityPosts
                Log.d("COMMUNITY/ALLPOST/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/ALLPOST/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun setPostLike(accessToken: String, postId: String, like: Boolean) {
        viewModelScope.launch {
            val response =
                communityRepository.setPostLike(accessToken, postId, CommunityPostLikeRequest(like))
            if (response.isSuccessful) {
                Log.d("COMMUNITY/LIKE/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/LIKE/F", response.errorBody()?.string()!!)
            }
        }
    }
}