package com.guerrero.melichallenge.login.service

import com.google.gson.annotations.SerializedName

data class APIAuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("toke_type") val toke_type: String,
    @SerializedName("expires_in") val expires_in: Long,
    @SerializedName("scope") val scope: String,
    @SerializedName("user_id") val user_id: String,
    @SerializedName("refresh_token") val refresh_token: String
)
