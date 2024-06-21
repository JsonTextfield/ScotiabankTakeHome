package com.jsontextfield.scotiabanktakehome.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsontextfield.scotiabanktakehome.data.model.GitHubRepo
import com.jsontextfield.scotiabanktakehome.data.model.GitHubUser
import com.jsontextfield.scotiabanktakehome.data.repositories.GitHubRepoRepository
import com.jsontextfield.scotiabanktakehome.data.repositories.GitHubUserRepository
import com.jsontextfield.scotiabanktakehome.data.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val gitHubUserRepository: Repository<GitHubUser?> = GitHubUserRepository(),
    private val gitHubRepoRepository: Repository<List<GitHubRepo>> = GitHubRepoRepository(),
) :
    ViewModel() {

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchText.asStateFlow()

    private val _userData: MutableStateFlow<GitHubUser?> = MutableStateFlow(null)
    val userData: StateFlow<GitHubUser?> get() = _userData.asStateFlow()

    private val _repos: MutableStateFlow<List<GitHubRepo>> = MutableStateFlow(emptyList())
    val repos: StateFlow<List<GitHubRepo>> get() = _repos.asStateFlow()

    fun getTotalForks(): Int {
        return _repos.value
            .map { repo -> repo.forks }
            .reduce { a, b -> a + b }
    }

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userData.value = gitHubUserRepository.getData(_searchText.value)
        }
    }

    fun getUserRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserData()
            _repos.value = gitHubRepoRepository.getData(_searchText.value)
        }
    }
}