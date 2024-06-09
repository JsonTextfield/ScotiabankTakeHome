package com.jsontextfield.scotiabanktakehome.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun UserInfo(userData: GitHubUser? = null) {
    AnimatedVisibility(
        enter = fadeIn(animationSpec = tween(500)) + slideInVertically(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(500)) + slideOutVertically(animationSpec = tween(1000)),
        visible = userData != null
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                userData?.avatarUrl,
                contentDescription = "avatar",
                modifier = Modifier.widthIn(max = 100.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                userData?.name ?: "",
                modifier = Modifier.padding(10.dp),
            )
        }
    }
}