package com.news.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.news.R
import com.news.home.componet.NewsCard
import com.news.home.componet.NewsSearchView
import com.news.ui.purple700
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private val viewModel by viewModels<FavouritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                FavouritesScreen()
            }
        }
    }

    @Composable
    private fun FavouritesScreen() {
        val news = viewModel.favouritesNews.observeAsState()
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favourites Screen")
                },
                navigationIcon = {
                    IconButton(onClick = { findNavController().popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                backgroundColor = purple700,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }) {
            Column {
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