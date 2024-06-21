package com.jsontextfield.scotiabanktakehome.data.repositories

import com.jsontextfield.scotiabanktakehome.data.model.GitHubUser
import com.jsontextfield.scotiabanktakehome.network.Downloader

class GitHubUserRepository : Repository<GitHubUser?> {
    override suspend fun getData(userId: String): GitHubUser? {
        return Downloader.downloadGitHubUserData(userId)
    }
}
