package com.jsontextfield.scotiabanktakehome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.jsontextfield.scotiabanktakehome.ui.components.RepoList
import com.jsontextfield.scotiabanktakehome.ui.components.SearchBar
import com.jsontextfield.scotiabanktakehome.ui.components.UserInfo
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory()
        val mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            ScotiabankTakeHomeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Surface(shadowElevation = 10.dp) {
                            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
                        }
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val context = LocalContext.current
                        val searchText by mainViewModel.searchText.collectAsState()
                        SearchBar(
                            searchText,
                            onTextChanged = { mainViewModel.onSearchTextChanged(it) },
                            onSearchButtonPressed = {
                                mainViewModel.getUserData(context)
                                mainViewModel.getUserRepos(context)
                            },
                        )

                        val user by mainViewModel.user.collectAsState()
                        user?.let {
                            UserInfo(it)
                        }

                        val repos by mainViewModel.repos.collectAsState()
                        RepoList(repos) {
                            startActivity(
                                Intent(this@MainActivity, RepoDetailsActivity::class.java).apply {
                                    putExtra("repo", it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}