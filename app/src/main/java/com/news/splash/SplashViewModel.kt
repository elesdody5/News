package com.news.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.R
import com.news.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _navigation = MutableLiveData<Int>()

    val navigation: LiveData<Int>
        get() = _navigation

    fun isFirstTimeCheck() {
        val isFirstTime = repository.isFirstTime()
        _navigation.value =
            if (isFirstTime) R.id.action_splashFragment_to_onBoardingFragment else R.id.action_splashFragment_to_homeFragment
    }
}