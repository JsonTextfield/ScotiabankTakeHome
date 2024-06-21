package com.jsontextfield.scotiabanktakehome.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jsontextfield.scotiabanktakehome.R
import com.jsontextfield.scotiabanktakehome.data.model.GitHubRepo
import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RepoDetailsScreen(
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    repo: GitHubRepo? = null,
) {
    var totalForks by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        totalForks = mainViewModel.getTotalForks()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(10.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            stringResource(id = R.string.back)
                        )
                    }
                },
                title = {
                    Text(
                        repo?.name ?: "",
                        maxLines = 1,
                        modifier = Modifier.basicMarquee(),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        },
    ) { innerPadding ->
        repo?.let { repo ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                if (repo.description.isNotBlank()) {
                    Text(repo.description)
                }
                Text(stringResource(R.string.last_updated, repo.lastUpdated))
                Text(pluralStringResource(R.plurals.stars, repo.stars, repo.stars))
                Row {
                    Text(pluralStringResource(R.plurals.forks, totalForks, totalForks))
                    // show a red star if the number of forks are greater than 5000
                    if (totalForks > 5000) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.Rounded.Star, null, tint = Color.Red)
                    }
                }
            }
        }
    }
}