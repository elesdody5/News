package com.news.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.data.Repository
import com.news.data.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _webUrl = MutableLiveData<String?>()

    val webUrl: LiveData<String?>
        get() = _webUrl

    val favouritesNews = repository.getFavourites()

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
}