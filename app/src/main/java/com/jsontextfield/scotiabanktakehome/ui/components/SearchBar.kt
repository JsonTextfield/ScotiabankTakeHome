package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.R

@Composable
fun SearchBar(
    text: String = "",
    onTextChanged: (String) -> Unit = {},
    onSearchButtonPressed: () -> Unit = {},
) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            text,
            modifier = Modifier.weight(1f),
            label = { Text("Enter a GitHub user ID") },
            onValueChange = onTextChanged,
            singleLine = true,
            suffix = {
                /*
                // shows the clear button
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = { onTextChanged("") },
                    ) {
                        Icon(Icons.Rounded.Clear, stringResource(R.string.clear))
                    }
                }
                */
            }
        )
        Spacer(Modifier.width(10.dp))
        FilledTonalButton(
            modifier = Modifier.height(IntrinsicSize.Max),
            enabled = text.isNotBlank(),
            onClick = { onSearchButtonPressed() },
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(stringResource(R.string.search))
        }
    }
}