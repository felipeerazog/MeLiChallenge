package com.guerrero.melichallenge.base.di

import android.app.Application
import android.content.Context
import com.guerrero.melichallenge.base.SHARED_PREFERENCE_APP_NAME
import com.guerrero.melichallenge.base.SHARED_PREFERENCE_TOKEN_KEY
import com.guerrero.melichallenge.base.OAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val AUTH_BASE_URL = "https://api.mercadolibre.com/oauth/"
private const val BASE_URL = "https://api.mercadolibre.com/sites/MLC/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @RetrofitQualifier
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(
        interceptor: OAuthInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideTokenInterceptor(app: Application): OAuthInterceptor {
        val token = app.getSharedPreferences(SHARED_PREFERENCE_APP_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCE_TOKEN_KEY, "") ?: ""
        return OAuthInterceptor(token)
    }

    @Provides
    @Singleton
    @AuthRetrofitQualifier
    fun provideAuthRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
