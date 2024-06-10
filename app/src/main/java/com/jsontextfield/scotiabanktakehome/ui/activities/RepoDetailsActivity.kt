package com.jsontextfield.scotiabanktakehome.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.ui.screens.RepoDetailsScreen
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme

class RepoDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo: GitHubRepo? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("repo", GitHubRepo::class.java)
            }
            else {
                intent.getParcelableExtra("repo")
            }
        val totalForks = intent.getIntExtra("totalForks", 0)

        enableEdgeToEdge()
        setContent {
            ScotiabankTakeHomeTheme {
                RepoDetailsScreen(repo, totalForks)
            }
        }
    }
}