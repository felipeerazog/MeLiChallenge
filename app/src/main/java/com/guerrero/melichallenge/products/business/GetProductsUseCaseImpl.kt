package com.guerrero.melichallenge.products.business

import com.guerrero.melichallenge.products.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetProductsUseCase {

    override suspend fun invoke(query: String): List<Product> {
        return repository.getProducts(query)
    }
}
