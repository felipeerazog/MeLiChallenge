package com.guerrero.melichallenge.products.di

import com.guerrero.melichallenge.base.di.RetrofitQualifier
import com.guerrero.melichallenge.products.business.GetProductsUseCase
import com.guerrero.melichallenge.products.business.GetProductsUseCaseImpl
import com.guerrero.melichallenge.products.repository.ProductRepository
import com.guerrero.melichallenge.products.repository.ProductRepositoryImpl
import com.guerrero.melichallenge.products.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class ProductsModule {

    @Provides
    fun provideGetProductsUseCase(
        productRepository: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCaseImpl(productRepository)
    }

    @Provides
    fun provideProductRepository(
        service: ProductService
    ): ProductRepository {
        return ProductRepositoryImpl(service)
    }

    @Provides
    fun provideSearchProductService(
        @RetrofitQualifier
        retrofit: Retrofit
    ): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}
