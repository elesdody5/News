package com.news.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.news.home.componet.NewsCard
import com.news.home.componet.NewsSearchView
import com.news.ui.purple700
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.webUrl.observe(viewLifecycleOwner) {
            it?.let {
                openWebPage(it)
                viewModel.clearUrl()
            }

        }
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen()
            }
        }
    }

    private fun openWebPage(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    @Composable
    private fun HomeScreen() {
        val news = viewModel.newsListLiveDate.observeAsState()
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "HeadLine Screen")
                },
                backgroundColor = purple700,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }) {
            Column {
                NewsSearchView(viewModel::onQueryChange, viewModel.selectedQuery)
                LazyColumn {
                    items(news.value ?: emptyList()) { news ->
                        NewsCard(
                            news,
                            viewModel::openWebPage,
                            viewModel::addToFavourite,
                            viewModel::removeFromFavourite
                        )
                    }
                }
            }
        }
    }
}