package com.jsontextfield.scotiabanktakehome.util

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser

/**
 * Singleton for downloading JSON data from GitHub
 */
object Downloader {
    private const val TAG = "Downloader"

    /**
     * Downloads GitHub user data
     * @param context a Context to be used by Volley
     * @param id a String that represents the username
     * @param onComplete a function that is called when data is successfully downloaded
     * @param onError a function that is called when an error has occurred
     */
    fun downloadGitHubUserData(
        context: Context,
        id: String,
        onComplete: (GitHubUser) -> Unit = {},
        onError: () -> Unit = {},
    ) {
        val url = "https://api.github.com/users/$id"
        val jsonObjectRequest = JsonObjectRequest(url, {
            onComplete(GitHubUser.fromJson(it))
        }, {
            Log.e(TAG, it.toString())
            onError()
        })
        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }

    /**
     * Downloads a GitHub user's repo info
     * @param context a Context to be used by Volley
     * @param id a String that represents the username
     * @param onComplete a function that is called when data is successfully downloaded
     * @param onError a function that is called when an error has occurred
     */
    fun downloadGitHubUserRepos(
        context: Context,
        id: String,
        onComplete: (List<GitHubRepo>) -> Unit = {},
        onError: () -> Unit = {},
    ) {
        val url = "https://api.github.com/users/$id/repos"
        val jsonArrayRequest = JsonArrayRequest(url, { jsonArray ->
            val result = ArrayList<GitHubRepo>()
            for (i in 0 until jsonArray.length()) {
                val repo = GitHubRepo.fromJson(jsonArray.getJSONObject(i))
                result.add(repo)
            }
            onComplete(result)
        }, {
            Log.e(TAG, it.toString())
            onError()
        })
        Volley.newRequestQueue(context).add(jsonArrayRequest)
    }
}