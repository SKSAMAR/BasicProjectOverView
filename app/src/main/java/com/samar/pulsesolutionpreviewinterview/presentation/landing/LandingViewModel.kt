package com.samar.pulsesolutionpreviewinterview.presentation.landing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.samar.pulsesolutionpreviewinterview.common.BaseViewModel
import com.samar.pulsesolutionpreviewinterview.data.dto.ProductsItem
import com.samar.pulsesolutionpreviewinterview.domain.model.ScreenState
import com.samar.pulsesolutionpreviewinterview.domain.useCases.getProducts.GetProductsUseCase
import com.samar.pulsesolutionpreviewinterview.util.ConnectionLiveData
import com.samar.pulsesolutionpreviewinterview.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LandingViewModel
@Inject constructor(
    val userCase: GetProductsUseCase,
    val connectionLiveData: ConnectionLiveData
) : BaseViewModel<ArrayList<ProductsItem?>>() {

    var isFetchingMore by mutableStateOf(false)
    var pageIndex = 1
    var pageSize = 10

    init {
        fetchAllProducts()
    }

    fun fetchAllProducts(initialFetch: Boolean = true, message: (error: String) -> Unit = {}) {
        userCase.invoke(PageIndex = pageIndex.toString(), PageSize = pageSize.toString())
            .onEach {
                if (initialFetch) {
                    when (it) {
                        is Resource.Error -> {
                            _state.value = ScreenState(error = it.message ?: "Some error")
                        }
                        is Resource.Loading -> {
                            _state.value = ScreenState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = ScreenState(receivedResponse = it.data)
                            pageIndex++
                        }
                    }
                } else {
                   when (it) {
                        is Resource.Error -> {
                            isFetchingMore = false
                            message(it.message ?: "Some Error")

                        }
                        is Resource.Loading -> {
                            isFetchingMore = true
                        }
                        is Resource.Success -> {
                            it.data?.let { moreList ->
                                _state.value.receivedResponse?.let { currentList ->
                                    currentList.addAll(moreList)
                                }
                            }
                            isFetchingMore = false
                            pageIndex++
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }


}