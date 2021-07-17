package com.news.onboarding

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.R
import com.news.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val categories = mutableListOf<String>()

    var selectedCountry by mutableStateOf("")
    var selectedCategory by mutableStateOf("")

    private val _errorMessage = MutableLiveData<Int>()

    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private val _navigation = MutableLiveData<Int>()
    val navigation: LiveData<Int>
        get() = _navigation


    fun onConfirmButtonClicked() {
        if (categories.size < 3 || selectedCountry.isEmpty()) {
            _errorMessage.value = R.string.onBoarding_error_message
        } else {
            repository.saveCountry(selectedCountry)
            repository.saveCategories(categories.toTypedArray())
            _navigation.value = R.id.action_onBoardingFragment_to_homeFragment
        }
    }

    fun addCategory(category: String) {
        if (categories.size < 3) {
            categories.add(category)
            selectedCategory = category
        } else
            _errorMessage.value = R.string.select_categories_error
    }
}