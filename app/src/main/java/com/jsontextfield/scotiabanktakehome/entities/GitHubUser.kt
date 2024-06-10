package com.jsontextfield.scotiabanktakehome.entities

import org.json.JSONObject

data class GitHubUser(
    val name: String = "",
    val avatarUrl: String = "",
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): GitHubUser {
            return GitHubUser(
                name = jsonObject.optString("name"),
                avatarUrl = jsonObject.optString("avatar_url"),
            )
        }
    }
}
