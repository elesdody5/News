package com.news.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.R
import com.news.data.Repository
import com.news.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.*
import java.util.concurrent.TimeoutException
import kotlin.jvm.Throws

class OnBoardingViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: Repository

    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        onBoardingViewModel = OnBoardingViewModel(repository)
    }

    @After
    fun clean() {
        clearAllMocks()
    }

    @Test
    fun onConfirmButtonClick_hasCountryAndCategories_navigateToHome() {

        onBoardingViewModel.country = "US"
        onBoardingViewModel.categories[0] = "1"
        onBoardingViewModel.categories[1] = "2"
        onBoardingViewModel.categories[2] = "3"
        onBoardingViewModel.onConfirmButtonClicked()
        val navigation = onBoardingViewModel.navigation.getOrAwaitValue()
        Assert.assertEquals(R.id.action_onBoardingFragment_to_homeFragment, navigation)
    }
}