package com.jsontextfield.scotiabanktakehome.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jsontextfield.scotiabanktakehome.ui.screens.MainScreen
import com.jsontextfield.scotiabanktakehome.ui.screens.RepoDetailsScreen
import com.jsontextfield.scotiabanktakehome.ui.theme.ScotiabankTakeHomeTheme
import com.jsontextfield.scotiabanktakehome.ui.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScotiabankTakeHomeTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<MainViewModel>()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            mainViewModel = viewModel,
                            onNavigateToRepo = { navController.navigate("repo/$it") },
                        )
                    }
                    composable(
                        "repo/{repo}",
                        arguments = listOf(
                            navArgument(name = "repo") {
                                nullable = true
                                defaultValue = ""
                                type = NavType.StringType
                            },
                        ),
                    ) { backStackEntry ->
                        val repos by viewModel.repos.collectAsStateWithLifecycle()
                        RepoDetailsScreen(
                            totalForks = viewModel.getTotalForks(),
                            repo = repos.find { it.name == backStackEntry.arguments?.getString("repo") },
                            onBackPressed = { navController.navigateUp() },
                        )
                    }
                }
            }
        }
    }
}