package com.guerrero.melichallenge.base

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION_HEADER = "Authorization"
private const val TOKEN_TYPE_BEARER = "bearer"

class OAuthInterceptor(
    private val accessToken: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header(
            name = AUTHORIZATION_HEADER,
            value = "$TOKEN_TYPE_BEARER $accessToken"
        ).build()
        return chain.proceed(request)
    }
}
