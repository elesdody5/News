package com.news.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.R
import com.news.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val categories = arrayOfNulls<String>(3)

    var country: String? = null

    private val _errorMessage = MutableLiveData<Int>()

    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private val _navigation = MutableLiveData<Int>()
    val navigation: LiveData<Int>
        get() = _navigation


    fun onConfirmButtonClicked() {
        if (categories.any { item -> item == null } || country == null) {
            _errorMessage.value = R.string.onBoarding_error_message
        } else {
            repository.saveCountry(country!!)
            repository.saveCategories(categories)
            _navigation.value = R.id.action_onBoardingFragment_to_homeFragment
        }
    }

}