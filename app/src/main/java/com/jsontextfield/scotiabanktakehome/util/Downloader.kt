package com.jsontextfield.scotiabanktakehome.util

import android.util.Log
import com.jsontextfield.scotiabanktakehome.entities.GitHubRepo
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Singleton for downloading JSON data from GitHub
 */
object Downloader {
    private const val TAG = "Downloader"

    private val client = OkHttpClient()

    /**
     * Downloads GitHub user data
     * @param id a String that represents the username
     * @param onComplete a function that is called when data is successfully downloaded
     * @param onError a function that is called when an error has occurred
     */
    fun downloadGitHubUserData(
        id: String,
        onComplete: (GitHubUser) -> Unit = {},
        onError: () -> Unit = {},
    ) {

        val url = "https://api.github.com/users/$id"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.toString())
                onError()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonObject = JSONObject(response.body?.string() ?: "")
                onComplete(GitHubUser.fromJson(jsonObject))
            }
        })
    }

    /**
     * Downloads a GitHub user's repo info
     * @param id a String that represents the username
     * @param onComplete a function that is called when data is successfully downloaded
     * @param onError a function that is called when an error has occurred
     */
    fun downloadGitHubUserRepos(
        id: String,
        onComplete: (List<GitHubRepo>) -> Unit = {},
        onError: () -> Unit = {},
    ) {
        val url = "https://api.github.com/users/$id/repos"
        val request = Request.Builder().url(url).build()
        
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.toString())
                onError()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonArray = JSONArray(response.body?.string() ?: "")
                val result = ArrayList<GitHubRepo>()
                for (i in 0 until jsonArray.length()) {
                    val repo = GitHubRepo.fromJson(jsonArray.getJSONObject(i))
                    result.add(repo)
                }
                onComplete(result)
            }
        })
    }
}