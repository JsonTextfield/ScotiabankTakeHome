package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    text: String,
    onTextChanged: (String) -> Unit = {},
    onSearchButtonPressed: () -> Unit = {},
) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            text,
            modifier = Modifier.weight(1f),
            label = { Text("Enter a GitHub user ID") },
            onValueChange = onTextChanged,
        )
        Spacer(modifier = Modifier.width(10.dp))
        FilledTonalButton(onClick = {
            // Search GitHub using text
            onSearchButtonPressed()
        }) {
            Text("Search")
        }
    }
}