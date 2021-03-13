package com.guerrero.melichallenge.login.business

typealias AccessToken = String

interface AuthUseCase {

    suspend operator fun invoke(uriString: String): AccessToken
}
