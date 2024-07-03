package com.f4.starwarsfactsapp.ui.screens

sealed class UIState {
    data object Loading : UIState()
    data object Success : UIState()
    data class Error(val msg: String) : UIState()
}