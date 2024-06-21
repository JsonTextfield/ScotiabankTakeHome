package com.jsontextfield.scotiabanktakehome.data.repositories

import com.jsontextfield.scotiabanktakehome.data.model.GitHubRepo
import com.jsontextfield.scotiabanktakehome.network.Downloader

class GitHubRepoRepository : Repository<List<GitHubRepo>> {
    override suspend fun getData(userId: String): List<GitHubRepo> {
        return Downloader.downloadGitHubUserRepos(userId)
    }
}
