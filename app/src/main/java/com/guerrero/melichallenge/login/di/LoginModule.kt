package com.guerrero.melichallenge.login.di

import com.guerrero.melichallenge.base.di.AuthRetrofitQualifier
import com.guerrero.melichallenge.login.business.AuthUseCase
import com.guerrero.melichallenge.login.business.AuthUseCaseImpl
import com.guerrero.melichallenge.login.business.UriHelper
import com.guerrero.melichallenge.login.business.UriHelperImpl
import com.guerrero.melichallenge.login.repository.AuthRepository
import com.guerrero.melichallenge.login.repository.AuthRepositoryImpl
import com.guerrero.melichallenge.login.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class LoginModule {

    @Provides
    fun provideAuthUseCase(
        repository: AuthRepository,
        uriHelper: UriHelper
    ): AuthUseCase {
        return AuthUseCaseImpl(repository, uriHelper)
    }

    @Provides
    fun provideAuthRepository(
        service: AuthService
    ): AuthRepository {
        return AuthRepositoryImpl(service)
    }

    @Provides
    fun provideUriHelper(): UriHelper {
        return UriHelperImpl()
    }

    @Provides
    fun provideAuthService(
        @AuthRetrofitQualifier
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}
