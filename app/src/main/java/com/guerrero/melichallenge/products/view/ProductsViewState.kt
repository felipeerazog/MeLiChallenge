package com.guerrero.melichallenge.products.view

import com.guerrero.melichallenge.products.business.Product

/**
 * Author: Felipe Guerrero
 */
open class ProductsViewState {
    class Loading : ProductsViewState()
    class Normal: ProductsViewState()
    class ShowProducts(val products: List<Product>): ProductsViewState()
}
