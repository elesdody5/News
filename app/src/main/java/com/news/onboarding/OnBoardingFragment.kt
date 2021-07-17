package com.news.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.news.R
import com.news.onboarding.component.ChipGrop
import com.news.onboarding.component.CountryDropDownList
import com.news.ui.AppTheme
import com.news.ui.purple700
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private val viewModel by viewModels<OnBoardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupNavigationListener()
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    OnBoardingScreen()
                }
            }
        }
    }

    private fun setupNavigationListener() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }
    }

    private fun setupErrorListener(scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(it), Toast.LENGTH_LONG).show()
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    getString(it)
                )
            }
        }
    }

    @Composable
    private fun OnBoardingScreen() {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        remember { setupErrorListener(scaffoldState, coroutineScope) }
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = { scaffoldState.snackbarHostState },
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "OnBoarding Screen")
                    },
                    backgroundColor = purple700,
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            }) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                CountryDropDownList(
                    requireContext().resources.getStringArray(R.array.countries),
                    onItemSelected = { country -> viewModel.selectedCountry = country },
                    selectedText = viewModel.selectedCountry
                )
                ChipGrop(
                    requireContext().resources.getStringArray(R.array.categories),
                    viewModel.selectedCategory,
                    onSelectedCategoryChanged = viewModel::addCategory
                )
                Button(onClick = viewModel::onConfirmButtonClicked) {
                    Text("Submit")
                }
            }
        }
    }
}