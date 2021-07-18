package com.news.onboarding.component


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ChipGrop(
    categoriesList: Array<String>,
    addCategory: (String) -> Boolean,
    removeCategory: (String) -> Unit,

    ) {

    ScrollableTabRow(
        selectedTabIndex = 2, modifier = Modifier.padding(vertical = 10.dp),
        backgroundColor = Color.White,
    ) {
        categoriesList.forEach { category ->
            CategoryChip(
                category,
                addCategory,
                removeCategory
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: String,
    addCategory: (String) -> Boolean,
    removeCategory: (String) -> Unit,
) {
    val isSelected = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (!isSelected.value) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .toggleable(
                    value = isSelected.value,
                    onValueChange = {
                        if (!isSelected.value) {
                            val added = addCategory(category)
                            if (added) isSelected.value = true
                        } else {
                            removeCategory(category)
                            isSelected.value = false
                        }
                    }
                )
        ) {
            if (isSelected.value)
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