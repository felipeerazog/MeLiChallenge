package com.guerrero.melichallenge.login.repository

import com.guerrero.melichallenge.login.service.AuthService

/**
 * Author: Felipe Guerrero
 */
class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository {

    override suspend fun auth(code: String): String {
        val response = service.auth(code = code)
        return response.accessToken
    }
}
