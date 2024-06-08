package com.jsontextfield.scotiabanktakehome.entities

import org.json.JSONObject

data class GitHubRepo(
    val name: String = "",
    val description: String = "",
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): GitHubRepo {
            return GitHubRepo(
                name = jsonObject.optString("name"),
                description = jsonObject.optString("description"),
            )
        }
    }
}
