package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo

@Composable
fun RepoDetails(repo: GitHubRepo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(repo.name)
        Text(repo.description)
        Text(repo.lastUpdated)
        Row {
            Text("${repo.stars} stars")
            if (repo.stars >= 5000) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Rounded.Star, "star", tint = Color.Red)
            }
        }
        Text("${repo.forks} forks")
    }
}