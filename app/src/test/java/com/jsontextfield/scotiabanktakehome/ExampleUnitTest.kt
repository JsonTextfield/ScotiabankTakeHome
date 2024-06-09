package com.jsontextfield.scotiabanktakehome

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    private var mockContext : Context = Mockito.mock(Context::class.java)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDownloadUserData() {
        val userId = "jsontextfield"
        Downloader.downloadGitHubUserData(mockContext, userId) {
            assertEquals("testing download GitHub user", "Jason Bromfield", it.name)
        }
    }
}