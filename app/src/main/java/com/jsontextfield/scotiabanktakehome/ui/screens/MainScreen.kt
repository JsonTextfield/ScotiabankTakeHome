package com.jsontextfield.scotiabanktakehome.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jsontextfield.scotiabanktakehome.R
import com.jsontextfield.scotiabanktakehome.ui.activities.RepoDetailsActivity
import com.jsontextfield.scotiabanktakehome.ui.components.RepoList
import com.jsontextfield.scotiabanktakehome.ui.components.SearchBar
import com.jsontextfield.scotiabanktakehome.ui.components.UserInfo
import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainState
import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(10.dp),
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context : Context = LocalContext.current
            val scope : CoroutineScope = rememberCoroutineScope()
            val mainState : MainState by mainViewModel.mainState.collectAsState()
            var isVisible : Boolean by rememberSaveable { mutableStateOf(false) }

            LaunchedEffect(mainState.lastUpdated) {
                delay(500)
                isVisible = true
            }

            SearchBar(
                mainState.searchText,
                onTextChanged = { mainViewModel.onSearchTextChanged(it) },
                onSearchButtonPressed = {
                    scope.launch {
                        isVisible = false
                        delay(500)
                        mainViewModel.getUserData(context)
                        mainViewModel.getUserRepos(context)
                    }
                },
            )

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(500)) +
                        slideInVertically(
                            animationSpec = tween(500),
                            initialOffsetY = { it / 10 },
                        ),
                exit = fadeOut(tween(500)),
            ) {
                UserInfo(mainState.user)
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(500, 500)) +
                        slideInVertically(
                            animationSpec = tween(500, 500),
                            initialOffsetY = { it / 10 },
                        ),
                exit = fadeOut(tween(500)),
            ) {
                RepoList(mainState.repos) {
                    // get the sum of all the forks of the user's repos
                    val totalForks: Int = mainState.repos
                        .map { repo -> repo.forks }
                        .reduce { a, b -> a + b }

                    val intent = Intent(context, RepoDetailsActivity::class.java)
                    intent.putExtra("repo", it)
                    intent.putExtra("totalForks", totalForks)
                    context.startActivity(intent)
                }
            }
        }
    }
}