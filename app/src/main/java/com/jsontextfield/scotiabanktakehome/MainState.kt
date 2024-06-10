package com.jsontextfield.scotiabanktakehome

import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser

data class MainState(
    val user: GitHubUser? = null,
    val repos: List<GitHubRepo> = emptyList(),
    val searchText: String = "",
    val lastUpdated: Long = 0L,
)