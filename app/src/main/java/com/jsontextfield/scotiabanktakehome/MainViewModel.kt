package com.jsontextfield.scotiabanktakehome

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private var _mainState: MutableStateFlow<MainState> = MutableStateFlow(MainState())

    val mainState: StateFlow<MainState>
        get() = _mainState

    fun onSearchTextChanged(newText: String) {
        _mainState.update { it.copy(searchText = newText) }
    }

    fun getUserData(context: Context) {
        Downloader.downloadGitHubUserData(context, _mainState.value.searchText) { user ->
            _mainState.update {
                it.copy(
                    user = user,
                    lastUpdated = System.currentTimeMillis(),
                )
            }
        }
    }

    fun getUserRepos(context: Context) {
        Downloader.downloadGitHubUserRepos(context, _mainState.value.searchText) { repos ->
            _mainState.update {
                it.copy(
                    repos = repos,
                    lastUpdated = System.currentTimeMillis(),
                )
            }
        }
    }
}