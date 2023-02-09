package com.samar.pulsesolutionpreviewinterview.data.remote

import com.samar.pulsesolutionpreviewinterview.data.dto.ReviewResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PreviewApi {

    @GET("products")
    suspend fun getProducts(
        @Query("PageIndex") PageIndex: String,
        @Query("PageSize") PageSize: String
    ): ReviewResponseDto
}