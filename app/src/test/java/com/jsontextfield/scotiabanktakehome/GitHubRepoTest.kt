package com.jsontextfield.scotiabanktakehome

import com.jsontextfield.scotiabanktakehome.data.model.GitHubRepo
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class GitHubRepoTest {
    /**
     * Tests GitHubRepo creation using the fromJson function
     */
    @Test
    fun testFromJson() {
        val jsonObject = JSONObject().apply {
            put("name", "my-repo")
            put("description", "this is a test of my repo")
            put("updated_at", "yesterday")
            put("stargazers_count", 1337)
            put("forks", 1024)
        }

        val repo = GitHubRepo.fromJson(jsonObject)

        assertEquals("my-repo", repo.name)
        assertEquals("this is a test of my repo", repo.description)
        assertEquals("yesterday", repo.lastUpdated)
        assertEquals(1337, repo.stars)
        assertEquals(1024, repo.forks)
    }

    /**
     * Tests the value of description if the JSONObject's description field is missing, null, or empty
     */
    @Test
    fun testInvalidDescription() {
        val jsonObject = JSONObject().apply {
            put("name", "my-repo")
        }
        var repo = GitHubRepo.fromJson(jsonObject)
        assertEquals("", repo.description)

        jsonObject.put("description", JSONObject.NULL)
        repo = GitHubRepo.fromJson(jsonObject)

        assertEquals("", repo.description)

        jsonObject.put("description", "")
        repo = GitHubRepo.fromJson(jsonObject)

        assertEquals("", repo.description)
    }

    /**
     * Tests GitHubRepo equality using the fromJson function
     */
    @Test
    fun testFromJsonEquality() {
        val repo1 = GitHubRepo(
            name = "my-repo",
            description = "this is a test of my repo",
            lastUpdated = "yesterday",
            stars = 1337,
            forks = 1024,
        )

        val jsonObject = JSONObject().apply {
            put("name", "my-repo")
            put("description", "this is a test of my repo")
            put("updated_at", "yesterday")
            put("stargazers_count", 1337)
            put("forks", 1024)
        }

        val repo2 = GitHubRepo.fromJson(jsonObject)

        assertEquals(repo1, repo2)
    }

    /**
     * Tests GitHubRepo values when created using fromJson with invalid keys
     */
    @Test
    fun testFromJsonInvalidKeys() {
        val jsonObject = JSONObject().apply {
            put("_name", "my-repo")
            put("_description", "this is a test of my repo")
            put("_updated_at", "yesterday")
            put("_stargazers_count", 1337)
            put("_forks", 1024)
        }

        val repo = GitHubRepo.fromJson(jsonObject)

        assertEquals("", repo.name)
        assertEquals("", repo.description)
        assertEquals("", repo.lastUpdated)
        assertEquals(0, repo.stars)
        assertEquals(0, repo.forks)
    }

    /**
     * Tests GitHubRepo values when created using fromJson with invalid values
     */
    @Test
    fun testFromJsonInvalidValues() {
        val jsonObject = JSONObject().apply {
            put("name", 0)
            put("description", true)
            put("updated_at", 12.34)
            put("stargazers_count", "zero")
            put("forks", JSONObject.NULL)
        }
        val repo = GitHubRepo.fromJson(jsonObject)

        assertEquals("0", repo.name)
        assertEquals("true", repo.description)
        assertEquals("12.34", repo.lastUpdated)
        assertEquals(0, repo.stars)
        assertEquals(0, repo.forks)
    }
}