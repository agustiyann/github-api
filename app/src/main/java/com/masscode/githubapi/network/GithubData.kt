package com.masscode.githubapi.network

import com.squareup.moshi.Json

data class GithubData(
    val id: String,
    @Json(name = "login") val username: String,
    val avatar_url: String,
    val bio: String?
)