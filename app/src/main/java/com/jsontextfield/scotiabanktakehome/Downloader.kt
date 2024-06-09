package com.jsontextfield.scotiabanktakehome

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser

object Downloader {
    private const val TAG = "Volley"

    fun downloadGitHubUserData(
        context: Context,
        id: String,
        onComplete: (GitHubUser) -> Unit = {}
    ) {
        val url = "https://api.github.com/users/$id"
        val jsonObjectRequest = JsonObjectRequest(url, {
            onComplete(GitHubUser.fromJson(it))
        }, {
            Log.e(TAG, it.toString())
        })
        Volley.newRequestQueue(context).add(jsonObjectRequest)
    }

    fun downloadGitHubUserRepos(
        context: Context,
        id: String,
        onComplete: (List<GitHubRepo>) -> Unit = {},
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
        })
        Volley.newRequestQueue(context).add(jsonArrayRequest)
    }
}