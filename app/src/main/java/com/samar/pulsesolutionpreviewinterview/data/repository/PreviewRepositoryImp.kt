package com.samar.pulsesolutionpreviewinterview.data.repository

import com.samar.pulsesolutionpreviewinterview.data.dto.ReviewResponseDto
import com.samar.pulsesolutionpreviewinterview.data.remote.PreviewApi
import com.samar.pulsesolutionpreviewinterview.domain.repository.PreviewRepository
import javax.inject.Inject

class PreviewRepositoryImp
@Inject constructor(private val previewApi: PreviewApi) : PreviewRepository {

    override suspend fun getProducts(PageIndex: String, PageSize: String): ReviewResponseDto {
        return previewApi.getProducts(PageIndex = PageIndex, PageSize = PageSize)
    }


}