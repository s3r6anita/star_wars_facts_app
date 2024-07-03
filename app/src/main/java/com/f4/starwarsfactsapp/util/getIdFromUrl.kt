package com.f4.starwarsfactsapp.util

fun getIdFromUrl(url: String): Int {
    val parts = url.trimEnd('/').split("/")
    return parts.lastOrNull()?.toIntOrNull() ?: -1
}