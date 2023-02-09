package com.samar.pulsesolutionpreviewinterview.domain.model

data class ScreenState<T>(
    val isLoading: Boolean = false,
    val error: String = "",
    val receivedResponse: T? = null,
)
