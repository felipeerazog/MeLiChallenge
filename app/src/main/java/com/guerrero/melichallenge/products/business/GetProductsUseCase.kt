package com.guerrero.melichallenge.products.business

interface GetProductsUseCase {

    suspend operator fun invoke(query: String): List<Product>
}
