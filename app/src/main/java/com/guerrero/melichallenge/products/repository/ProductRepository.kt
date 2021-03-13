package com.guerrero.melichallenge.products.repository

import com.guerrero.melichallenge.products.business.Product

/**
 * Author: Felipe Guerrero
 */
interface ProductRepository {

    suspend fun getProducts(query: String): List<Product>
}
