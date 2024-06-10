package com.jsontextfield.scotiabanktakehome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jsontextfield.scotiabanktakehome.ui.components.RepoList
import com.jsontextfield.scotiabanktakehome.ui.components.SearchBar
import com.jsontextfield.scotiabanktakehome.ui.components.UserInfo
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ScotiabankTakeHomeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(10.dp),
                            title = { Text(stringResource(R.string.app_name)) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Unspecified)
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val context = LocalContext.current
                        val scope = rememberCoroutineScope()
                        val mainViewModel = viewModel<MainViewModel>()
                        val mainState by mainViewModel.mainState.collectAsState()
                        val animationState = remember { MutableTransitionState(false) }

                        LaunchedEffect(mainState.lastUpdated) {
                            delay(1000)
                            animationState.targetState = true
                        }

                        SearchBar(
                            mainState.searchText,
                            onTextChanged = { mainViewModel.onSearchTextChanged(it) },
                            onSearchButtonPressed = {
                                scope.launch {
                                    animationState.targetState = false
                                    delay(1000)
                                    mainViewModel.getUserData(context)
                                    mainViewModel.getUserRepos(context)
                                }
                            },
                        )

                        AnimatedVisibility(
                            visibleState = animationState,
                            enter = fadeIn(
                                tween(500)
                            ) + slideInVertically(
                                animationSpec = tween(500),
                                initialOffsetY = { it / 10 },
                            ),
                            exit = fadeOut(tween(500)),
                        ) {
                            UserInfo(mainState.user)
                        }

                        AnimatedVisibility(
                            visibleState = animationState,
                            enter = fadeIn(
                                tween(500, 200)
                            ) + slideInVertically(
                                animationSpec = tween(500, 200),
                                initialOffsetY = { it / 10 },
                            ),
                            exit = fadeOut(tween(500)),
                        ) {
                            RepoList(mainState.repos) {
                                // get the sum of all the forks of the user's repos
                                val totalForks: Int = mainState.repos
                                    .map { it.forks }
                                    .reduce { a, b -> a + b }

                                val intent = Intent(context, RepoDetailsActivity::class.java)
                                intent.putExtra("repo", it)
                                intent.putExtra("totalForks", totalForks)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}