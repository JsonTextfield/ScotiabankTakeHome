package com.jsontextfield.scotiabanktakehome.entities

import org.json.JSONObject

data class GitHubUser(
    val name: String = "",
    val avatarUrl: String = "",
    val repos: List<GitHubRepo> = emptyList()
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): GitHubUser {
            return GitHubUser(
                name = jsonObject.optString("login"),
                avatarUrl = jsonObject.optString("avatar_url"),
            )
        }
    }
}
