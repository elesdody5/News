package com.news.home.componet

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.news.R


@Composable
fun HomeMenuItem(
    onItemClicked: (Int) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    IconButton(onClick = { expanded.value = true }) {
        Column {
            Icon(Icons.Default.Menu, "menue")
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    onItemClicked.invoke(R.id.action_homeFragment_to_favouritesFragment)
                }) {
                    Text("Favourites")
                }
            }
        }
    }
}
