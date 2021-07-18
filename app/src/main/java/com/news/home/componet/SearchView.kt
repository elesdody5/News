package com.news.home.componet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NewsSearchView(
    onQueryChange: (String) -> Unit,
    query: String,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            value = query,
            onValueChange = {
                onQueryChange.invoke(it)
            },
            label = {
                Text(text = "Search")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            leadingIcon = {
                Icon(Icons.Filled.Search, "searchIcon")
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        )
    }
}