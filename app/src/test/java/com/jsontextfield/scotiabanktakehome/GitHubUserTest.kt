package com.jsontextfield.scotiabanktakehome

import com.jsontextfield.scotiabanktakehome.entities.GitHubUser
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class GitHubUserTest {

    /**
     * Tests GitHubUser creation using the fromJson function
     */
    @Test
    fun testFromJson() {
        val jsonObject = JSONObject().apply {
            put("name", "Jason Bromfield")
            put("avatar_url", "someUrl")
        }

        val user = GitHubUser.fromJson(jsonObject)

        assertEquals("Jason Bromfield", user.name)
        assertEquals("someUrl", user.avatarUrl)
    }

    /**
     * Tests GitHubUser equality using the fromJson function
     */
    @Test
    fun testFromJsonEquality() {
        val user1 = GitHubUser("Jason Bromfield", "someUrl")

        val jsonObject = JSONObject().apply {
            put("name", "Jason Bromfield")
            put("avatar_url", "someUrl")
        }

        val user2 = GitHubUser.fromJson(jsonObject)

        assertEquals(user1, user2)
    }

    /**
     * Tests GitHubUser values when created using fromJson with invalid keys
     */
    @Test
    fun testFromJsonInvalidKeys() {
        val jsonObject = JSONObject().apply {
            put("name_invalid", "Jason Bromfield")
            put("avatar_url_invalid", "someUrl")
        }

        val user = GitHubUser.fromJson(jsonObject)

        assertEquals("", user.name)
        assertEquals("", user.avatarUrl)
    }

    /**
     * Tests GitHubUser values when created using fromJson with invalid values
     */
    @Test
    fun testFromJsonInvalidValues() {
        val jsonObject = JSONObject().apply {
            put("name", true)
            put("avatar_url", 0.0)
        }

        val user = GitHubUser.fromJson(jsonObject)

        assertEquals("true", user.name)
        assertEquals("0.0", user.avatarUrl)
    }
}