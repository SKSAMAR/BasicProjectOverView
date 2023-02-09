package com.samar.pulsesolutionpreviewinterview.domain.repository

import com.samar.pulsesolutionpreviewinterview.data.dto.ReviewResponseDto

interface PreviewRepository {

    suspend fun getProducts(
        PageIndex: String,
        PageSize: String
    ): ReviewResponseDto

}