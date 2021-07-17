package com.news.onboarding.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CountryDropDownList(
    countries: Array<String>,
    onItemSelected: (String) -> Unit,
    selectedText: String
) {
    val expanded = remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Select country code") },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    "contentDescription",
                    Modifier.clickable { expanded.value = !expanded.value })
            }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            countries.forEach { country ->
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    onItemSelected.invoke(country)
                }) {
                    Text(country)
                }
            }
        }
    }
}