package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
    AnimatedVisibility(
        visible = repos.isNotEmpty(),
        enter = fadeIn(animationSpec = tween(500)) + slideInVertically(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(500)) + slideOutVertically(animationSpec = tween(1000)),
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
                        supportingContent = {
                            if (repo.description != "null" && repo.description.isNotEmpty()) {
                                Text(repo.description)
                            }
                        },
                    )
                }
            }
        }
    }
}