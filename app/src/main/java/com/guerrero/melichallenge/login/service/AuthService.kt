package com.guerrero.melichallenge.login.service

import com.guerrero.melichallenge.base.APP_ID
import com.guerrero.melichallenge.base.REDIRECT_URI
import com.guerrero.melichallenge.base.SECRET_KEY
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val AUTH_ENDPOINT = "token"
private const val GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code"

interface AuthService {

    @FormUrlEncoded
    @POST(AUTH_ENDPOINT)
    suspend fun auth(
        @Field("grant_type") grantType: String = GRANT_TYPE_AUTHORIZATION_CODE,
        @Field("client_id") clientId: String = APP_ID,
        @Field("client_secret") clientSecret: String = SECRET_KEY,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = REDIRECT_URI
    ): APIAuthResponse
}
