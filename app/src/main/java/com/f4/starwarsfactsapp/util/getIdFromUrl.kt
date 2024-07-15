package com.f4.starwarsfactsapp.util

fun getIdFromUrl(url: String): Int {
    val parts = url.trimEnd('/').split("/")
    var id = parts.lastOrNull()?.toIntOrNull()?.minus(1) ?: -1
    // минус нужен, т.к. в API первый персонаж имеет id = 1
    if (id >= 17) id -= 1
    // id >= 17 - хардкод, в API нет персонажа с @Path("id") = 17
    //  при использовании Room с автогенерацией id, проблемы бы не было
    return id
}

fun getIdForUrl(id: Int): Int {
    return if (id >= 16) id + 2 else id + 1
}

fun getPageFromUrl(url: String?): Int {
    val parts = url?.split("?page=")
    return parts?.lastOrNull()?.toIntOrNull() ?: -1
}