package com.guerrero.melichallenge.products.service

import com.google.gson.annotations.SerializedName

/**
 * Author: Felipe Guerrero
 */
data class APISearchResult(
    @SerializedName("results")
    val results: List<APIItem>
)
