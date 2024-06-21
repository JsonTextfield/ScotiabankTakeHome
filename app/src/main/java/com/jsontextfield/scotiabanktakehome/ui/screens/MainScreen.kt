package com.jsontextfield.scotiabanktakehome.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jsontextfield.scotiabanktakehome.R
import com.jsontextfield.scotiabanktakehome.ui.components.RepoList
import com.jsontextfield.scotiabanktakehome.ui.components.SearchBar
import com.jsontextfield.scotiabanktakehome.ui.components.UserInfo
import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController = rememberNavController(),
) {
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
            val scope: CoroutineScope = rememberCoroutineScope()
            val searchText by mainViewModel.searchText.collectAsStateWithLifecycle()
            val user by mainViewModel.userData.collectAsStateWithLifecycle()
            val repos by mainViewModel.repos.collectAsStateWithLifecycle()
            var isVisible: Boolean by rememberSaveable { mutableStateOf(false) }
            var lastUpdated by remember { mutableLongStateOf(0L) }

            LaunchedEffect(user, repos, lastUpdated) {
                delay(500)
                isVisible = true
            }

            SearchBar(
                searchText,
                onTextChanged = { mainViewModel.onSearchTextChanged(it) },
                onSearchButtonPressed = {
                    scope.launch {
                        isVisible = false
                        delay(500)
                        mainViewModel.getUserData()
                        mainViewModel.getUserRepos()
                        lastUpdated = System.currentTimeMillis()
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
                UserInfo(user)
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
                RepoList(repos) {
                    navController.navigate("repo/${it.name}")
                }
            }
        }
    }
}