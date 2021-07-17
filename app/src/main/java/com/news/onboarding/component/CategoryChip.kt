package com.news.onboarding.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp


@Composable
fun ChipGrop(
    categoriesList: Array<String>,
    selectedCategory: String,
    onSelectedCategoryChanged: (String) -> Unit
) {

    ScrollableTabRow(
        selectedTabIndex = 2, modifier = Modifier.padding(vertical = 10.dp),
        backgroundColor = Color.White,
    ) {
        categoriesList.forEach { category ->
            CategoryChip(
                category,
                selectedCategory == category,
                onSelectedCategoryChanged
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (!isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChanged(category)
                }
            )
        ) {
            if (isSelected)
                Icon(
                    Icons.Default.Check,
                    "checked"
                )
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}