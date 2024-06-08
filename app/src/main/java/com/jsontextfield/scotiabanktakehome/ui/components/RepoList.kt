package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo

@Composable
fun RepoList(
    repos: List<GitHubRepo> = emptyList(),
    onItemClick: (GitHubRepo) -> Unit = {},
) {
    LazyColumn {
        items(repos) { repo ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onItemClick(repo) },
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                ListItem(
                    headlineContent = { Text(repo.name) },
                    supportingContent = { Text(repo.description) },
                )
            }
        }
    }
}