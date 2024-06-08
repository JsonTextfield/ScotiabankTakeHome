package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jsontextfield.scotiabanktakehome.entities.GitHubUser

@Composable
fun UserInfo(userData: GitHubUser) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            userData.avatarUrl,
            contentDescription = "avatar",
            modifier = Modifier.widthIn(max = 100.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(userData.name)
    }
}