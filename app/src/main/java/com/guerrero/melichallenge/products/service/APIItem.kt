package com.guerrero.melichallenge.products.service

import com.google.gson.annotations.SerializedName
import com.guerrero.melichallenge.products.business.Product

/**
 * Author: Felipe Guerrero
 */
data class APIItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("thumbnail")
    val thumbnail: String
) {

    fun mapToProduct(): Product {
        return Product(id, title, price.toLong(), thumbnail)
    }
}
