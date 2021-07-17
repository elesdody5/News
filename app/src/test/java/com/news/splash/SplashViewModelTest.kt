package com.news.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.R
import com.news.data.Repository
import com.news.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        splashViewModel = SplashViewModel(repository)
    }

    @After
    fun clean() {
        clearAllMocks()
    }

    @Test
    fun isFirstTime_repositoryReturnTrue_navigateToOnBoarding() {
        every { repository.isFirstTime() } returns true
        splashViewModel.isFirstTimeCheck()

        val navigation = splashViewModel.navigation.getOrAwaitValue()

        assertEquals(R.id.action_splashFragment_to_onBoardingFragment, navigation)
    }

    @Test
    fun isFirstTime_repositoryReturnFalse_navigateToHome() {
        every { repository.isFirstTime() } returns false
        splashViewModel.isFirstTimeCheck()

        val navigation = splashViewModel.navigation.getOrAwaitValue()

        assertEquals(R.id.action_splashFragment_to_homeFragment, navigation)
    }
}