package com.jsontextfield.scotiabanktakehome.data.model

import org.json.JSONObject

data class GitHubRepo(
    val name: String = "",
    val description: String = "",
    val lastUpdated: String = "",
    val stars: Int = 0,
    val forks: Int = 0,
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): GitHubRepo {
            val description = if (jsonObject.isNull("description")) {
                ""
            }
            else {
                jsonObject.getString("description")
            }
            return GitHubRepo(
                name = jsonObject.optString("name"),
                description = description,
                lastUpdated = jsonObject.optString("updated_at"),
                stars = jsonObject.optInt("stargazers_count"),
                forks = jsonObject.optInt("forks"),
            )
        }
    }
}
