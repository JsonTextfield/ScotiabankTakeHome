package com.jsontextfield.scotiabanktakehome.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jsontextfield.scotiabanktakehome.util.Downloader
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

    fun getUserData() {
        Downloader.downloadGitHubUserData(
            _mainState.value.searchText,
            onComplete = { user ->
                _mainState.update {
                    it.copy(
                        user = user,
                        lastUpdated = System.currentTimeMillis(),
                    )
                }
            },
            onError = {
                // update the UI state to show an error
            },
        )
    }

    fun getUserRepos() {
        Downloader.downloadGitHubUserRepos(
            _mainState.value.searchText,
            onComplete = { repos ->
                _mainState.update {
                    it.copy(
                        repos = repos,
                        lastUpdated = System.currentTimeMillis(),
                    )
                }
            },
            onError = {
                // update the UI state to show an error
            },
        )
    }
}