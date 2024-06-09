package com.jsontextfield.scotiabanktakehome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private var _user: MutableStateFlow<GitHubUser?> = MutableStateFlow(null)

    val user: StateFlow<GitHubUser?>
        get() = _user

    private var _repos: MutableStateFlow<List<GitHubRepo>> = MutableStateFlow(emptyList())

    val repos: StateFlow<List<GitHubRepo>>
        get() = _repos

    private var _searchText: MutableStateFlow<String> = MutableStateFlow("")

    val searchText: StateFlow<String>
        get() = _searchText

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }

    fun getUserData(context: Context) {
        _user.value = null
        Downloader.downloadGitHubUserData(context, searchText.value) {
            _user.value = it
        }
    }

    fun getUserRepos(context: Context) {
        _repos.value = emptyList()
        Downloader.downloadGitHubUserRepos(context, searchText.value) {
            _repos.value = it
        }
    }
}