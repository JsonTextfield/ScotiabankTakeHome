package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.jsontextfield.scotiabanktakehome.R

@Composable
fun BackButton() {
    val context = LocalContext.current
    IconButton(
        onClick = {
            (context as ComponentActivity).onBackPressedDispatcher.onBackPressed()
        },
    ) {
        Icon(Icons.Rounded.ArrowBack, stringResource(id = R.string.back))
    }
}