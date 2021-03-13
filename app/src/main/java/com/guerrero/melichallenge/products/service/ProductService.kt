package com.guerrero.melichallenge.products.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("search")
    suspend fun search(
        @Query("q") query: String
    ): APISearchResult
}
