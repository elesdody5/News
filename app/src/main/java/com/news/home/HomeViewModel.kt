package com.news.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.news.data.Repository
import com.news.data.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _webUrl = MutableLiveData<String?>()

    var selectedCategory by mutableStateOf("")

    val webUrl: LiveData<String?>
        get() = _webUrl

    private val _newsList = MutableLiveData<List<Article>>()
    private var _allList = listOf<Article>()

    private val _navigation = MutableLiveData<Int?>()
    val navigation: LiveData<Int?>
        get() = _navigation

    val categories = repository.getCategories()

    var newsListLiveDate = repository.getNewsList().switchMap {
        _allList = it
        _newsList.value = it
        _newsList
    }
    var selectedQuery: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            repository.refreshList(categories?.elementAt(0))
            selectedCategory = categories?.elementAt(0) ?: ""
        }
    }

    fun onQueryChange(query: String) {
        selectedQuery = query
        filterTasks(query)
    }

    fun openWebPage(url: String) {
        _webUrl.value = url
    }

    fun addToFavourite(article: Article) {
        article.favourite = true
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }

    fun removeFromFavourite(article: Article) {
        article.favourite = false
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }

    fun clearUrl() {
        _webUrl.value = null
    }

    fun clearNavigation() {
        _navigation.value = null
    }

    fun navigate(destination: Int) {
        _navigation.value = destination
    }

    private fun filterTasks(query: String) {
        if (query.isNotEmpty()) {
            val filteredList =
                _newsList.value?.filter { article ->
                    article.title.lowercase().contains(query.lowercase())
                }
            filteredList?.let {
                _newsList.value = it
            }
        } else {
            updateData(category = selectedCategory)
        }
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        viewModelScope.launch {
            repository.refreshList(category)
            updateData(category)
        }
    }

    private fun updateData(category: String) {
        _newsList.value = _allList
        if (category.isNotEmpty()) {
            val filteredList =
                _newsList.value?.filter { article ->
                    article.category == category
                }

            filteredList?.let {
                _newsList.value = it
            }
        }
    }
}