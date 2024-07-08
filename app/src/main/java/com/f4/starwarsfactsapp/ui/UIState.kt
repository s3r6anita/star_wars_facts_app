package com.f4.starwarsfactsapp.ui

sealed interface UIState {
    data object Loading : UIState
    data class Success<T>(val data: T) : UIState
    data class Error<T>(val msg: String, val data: T? = null) : UIState
}