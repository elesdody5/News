package com.news.home

import androidx.lifecycle.ViewModel
import com.news.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val newsListLiveDate = repository.getNewsList()
}