package com.guerrero.melichallenge.login.business

import com.guerrero.melichallenge.login.repository.AuthRepository

private const val CODE_PARAM = "code"

class AuthUseCaseImpl(
    private val repository: AuthRepository,
    private val uriHelper: UriHelper
) : AuthUseCase {

    override suspend fun invoke(uriString: String): AccessToken {
        val code = uriHelper.getParam(uriString, CODE_PARAM)
        return repository.auth(code)
    }
}
