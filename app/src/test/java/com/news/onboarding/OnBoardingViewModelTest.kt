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

        onBoardingViewModel.selectedCountry = "US"
        onBoardingViewModel.addCategory("1")
        onBoardingViewModel.addCategory("2")
        onBoardingViewModel.addCategory("3")
        onBoardingViewModel.onConfirmButtonClicked()
        val navigation = onBoardingViewModel.navigation.getOrAwaitValue()
        Assert.assertEquals(R.id.action_onBoardingFragment_to_homeFragment, navigation)
    }

    @Test
    fun onConfirmButtonClick_hasNoCountryORCategories_showError() {
        onBoardingViewModel.onConfirmButtonClicked()
        val error = onBoardingViewModel.errorMessage.getOrAwaitValue()
        Assert.assertEquals(R.string.onBoarding_error_message, error)
    }

    @Test
    fun addCategories_moreThanThree_showErrorMessage() {
        onBoardingViewModel.addCategory("1")
        onBoardingViewModel.addCategory("2")
        onBoardingViewModel.addCategory("3")
        onBoardingViewModel.addCategory("4")
        val error = onBoardingViewModel.errorMessage.getOrAwaitValue()
        Assert.assertEquals(R.string.select_categories_error, error)
    }
}