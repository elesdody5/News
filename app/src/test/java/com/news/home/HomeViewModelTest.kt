package com.news.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.news.MainCoroutineRule
import com.news.data.Repository
import com.news.data.entity.Article
import com.news.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: Repository

    private lateinit var homeViewModel: HomeViewModel

    private val mockCategories = setOf("1", "2", "3")
    private val newsList = MutableLiveData<List<Article>>()

    @After
    fun clean() {
        clearAllMocks()
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        newsList.value = listOf(
            Article(title = "1", category = "1", favourite = true),
            Article(title = "2", category = "2", favourite = false),
            Article(title = "3", category = "3", favourite = false),
        )
        every { repository.getCategories() } returns mockCategories
        every { repository.getNewsList() } returns newsList
        homeViewModel = HomeViewModel(repository)
        homeViewModel.newsListLiveDate.getOrAwaitValue()
    }

    @Test
    fun initViewModel_getListFromRepository() {
        coVerify { repository.refreshList(mockCategories.elementAt(0)) }
        assertEquals(mockCategories.elementAt(0), homeViewModel.selectedCategory)
    }

    @Test
    fun onQueryChange_titleInTheList_filterListWithTitle() {
        homeViewModel.onQueryChange("1")
        val list = homeViewModel.newsListLiveDate.getOrAwaitValue()
        assertEquals(1, list.size)
    }

    @Test
    fun onQueryChange_titleNotInTheList_noNewsFound() {
        homeViewModel.onQueryChange("4")
        val list = homeViewModel.newsListLiveDate.getOrAwaitValue()
        assertEquals(0, list.size)
    }

    @Test
    fun onCategoryChange_updateListWithCategory() {
        homeViewModel.onCategorySelected("2")
        val list = homeViewModel.newsListLiveDate.getOrAwaitValue()
        assertEquals("2", list[0].category)
    }
}