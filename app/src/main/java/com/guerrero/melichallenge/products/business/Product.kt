package com.guerrero.melichallenge.products.business

import java.text.DecimalFormat

data class Product(
    val id: String,
    val title: String,
    val price: Long,
    val thumbnail: String
) {

    fun getPriceInCurrencyFormat(): String {
        return DecimalFormat("$###,###,###").format(price)
    }
}
