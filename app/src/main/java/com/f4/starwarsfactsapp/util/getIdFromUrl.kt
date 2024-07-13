package com.f4.starwarsfactsapp.util

fun getIdFromUrl(url: String): Int {
    val parts = url.trimEnd('/').split("/")
    return (parts.lastOrNull()?.toIntOrNull()?.minus(1)) ?: -1
    // минус нужен, т.к. в API первый персонаж имеет id = 1
}

fun getIdForUrl(id: Int): Int {
    return id + 1
}

fun getPageFromUrl(url: String?): Int {
    val parts = url?.split("?page=")
    return parts?.lastOrNull()?.toIntOrNull() ?: -1
}