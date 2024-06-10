package com.jsontextfield.scotiabanktakehome.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.ui.components.BackButton
import com.jsontextfield.scotiabanktakehome.ui.components.RepoDetails

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RepoDetailsScreen(
    repo: GitHubRepo? = null,
    forks: Int = 0,
) {
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
                forks,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            )
        }
    }
}