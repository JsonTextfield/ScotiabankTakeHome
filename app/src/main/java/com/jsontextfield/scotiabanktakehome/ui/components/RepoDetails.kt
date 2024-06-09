package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jsontextfield.scotiabanktakehome.R
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo

@Composable
fun RepoDetails(repo: GitHubRepo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        if (repo.description != "null" && repo.description.isNotBlank()) {
            Text(repo.description)
        }
        Text(stringResource(id = R.string.last_updated, repo.lastUpdated))
        Text(pluralStringResource(id = R.plurals.stars, count = repo.stars, repo.stars))
        Row {
            Text(pluralStringResource(id = R.plurals.forks, count = repo.forks, repo.forks))
            if (repo.forks > 5000) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Rounded.Star, null, tint = Color.Red)
            }
        }
    }
}