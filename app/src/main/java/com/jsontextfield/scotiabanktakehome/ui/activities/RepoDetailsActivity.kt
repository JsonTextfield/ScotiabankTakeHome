package com.jsontextfield.scotiabanktakehome.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.ui.components.BackButton
import com.jsontextfield.scotiabanktakehome.ui.components.RepoDetails
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme

class RepoDetailsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(10.dp),
                            navigationIcon = { BackButton() },
                            title = {
                                Text(
                                    repo?.name ?: "",
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee(),
                                )
                            },
                        )
                    },
                ) { innerPadding ->
                    repo?.let {
                        RepoDetails(
                            repo,
                            totalForks,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}