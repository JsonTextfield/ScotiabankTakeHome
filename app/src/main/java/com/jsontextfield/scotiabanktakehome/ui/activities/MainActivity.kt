package com.jsontextfield.scotiabanktakehome.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jsontextfield.scotiabanktakehome.ui.screens.MainScreen
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ScotiabankTakeHomeTheme {
                MainScreen()
            }
        }
    }
}