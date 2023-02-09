package com.samar.pulsesolutionpreviewinterview.domain.useCases.getProducts

import com.samar.pulsesolutionpreviewinterview.data.dto.ProductsItem
import com.samar.pulsesolutionpreviewinterview.domain.repository.PreviewRepository
import com.samar.pulsesolutionpreviewinterview.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductsUseCase
@Inject constructor(private val repository: PreviewRepository) {

    operator fun invoke(
        PageIndex: String,
        PageSize: String
    ): Flow<Resource<ArrayList<ProductsItem?>?>> =
        flow {
            try {
                emit(Resource.Loading())
                val products = repository.getProducts(
                    PageSize = PageSize,
                    PageIndex = PageIndex
                )
                if (products.flgIsSuccess == true) {
                    emit(Resource.Success(products.products))
                } else {
                    emit(Resource.Error(products.stMessage ?: "Some failure"))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection."
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection."
                    )
                )
            }
        }
}