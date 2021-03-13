package com.guerrero.melichallenge.login.repository

/**
 * Author: Felipe Guerrero
 */
interface AuthRepository {

    suspend fun auth(code: String): String
}
