package com.guerrero.melichallenge.products.repository

import com.guerrero.melichallenge.products.business.Product
import com.guerrero.melichallenge.products.service.ProductService
import javax.inject.Inject

/**
 * Author: Felipe Guerrero
 */
class ProductRepositoryImpl @Inject constructor(
    private val service: ProductService
) : ProductRepository {

    override suspend fun getProducts(query: String): List<Product> {
        val response = service.search(query)
        println("FELO products reponse!!!: ${response.toString()}")
        return response.results.map { it.mapToProduct() }
    }
}
