package com.greenfriends.zeroway.presentation.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.*
import com.greenfriends.zeroway.data.repository.mypage.NoticeRepository
import kotlinx.coroutines.launch

class NoticeViewModel(private val noticeRepository: NoticeRepository) : ViewModel() {

    private val _announceId = MutableLiveData<Long>()
    val announceId: LiveData<Long> = _announceId

    private val _noticeResponse = MutableLiveData<List<NoticeResponse>>()
    val noticeResponse: LiveData<List<NoticeResponse>> = _noticeResponse

    private val _noticeDetailResponse = MutableLiveData<NoticeDetailResponse>()
    val noticeDetailResponse: LiveData<NoticeDetailResponse> = _noticeDetailResponse

    fun setAnnounceId(annouceId: Long) {
        _announceId.value = annouceId
    }

    fun getAnnounceId(): Long? {
        return announceId.value
    }

    fun getNotice() {
        viewModelScope.launch {
            val response = noticeRepository.getNotice()
            if (response.isSuccessful) {
                _noticeResponse.value = response.body()!!
                Log.d("NOTICE/S", response.body().toString())
            } else {
                Log.d("NOTICE/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getNoticeDetail(announceId: Long) {
        viewModelScope.launch {
            val response = noticeRepository.getNoticeDetail(announceId)
            if (response.isSuccessful) {
                _noticeDetailResponse.value = response.body()!!

                Log.d("NOTICE-D/S", response.body().toString())
            } else {
                Log.d("NOTICE-D/F", response.errorBody()?.string()!!)
            }
        }
    }

}