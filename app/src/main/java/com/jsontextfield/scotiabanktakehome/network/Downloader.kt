package com.jsontextfield.scotiabanktakehome.network

import android.util.Log
import com.jsontextfield.scotiabanktakehome.data.model.GitHubRepo
import com.jsontextfield.scotiabanktakehome.data.model.GitHubUser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

/**
 * Singleton for downloading JSON data from GitHub
 */
object Downloader {
    private const val TAG = "Downloader"

    private val client = OkHttpClient()

    /**
     * Downloads GitHub user data
     * @param id a String that represents the username
     */
    fun downloadGitHubUserData(
        id: String
    ): GitHubUser? {

        val url = "https://api.github.com/users/$id"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val jsonObject = JSONObject(response.body?.string() ?: "")
            return GitHubUser.fromJson(jsonObject)
        }
        Log.e(TAG, response.message)
        return null
    }

    /**
     * Downloads a GitHub user's repo info
     * @param id a String that represents the username
     */
    fun downloadGitHubUserRepos(id: String): List<GitHubRepo> {
        val url = "https://api.github.com/users/$id/repos"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val jsonArray = JSONArray(response.body?.string() ?: "")
            val result = ArrayList<GitHubRepo>()
            for (i in 0 until jsonArray.length()) {
                val repo = GitHubRepo.fromJson(jsonArray.getJSONObject(i))
                result.add(repo)
            }
            return result
        }
        Log.e(TAG, response.message)
        return emptyList()
    }
}