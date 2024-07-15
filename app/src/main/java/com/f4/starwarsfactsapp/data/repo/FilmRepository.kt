package com.f4.starwarsfactsapp.data.repo

import com.f4.starwarsfactsapp.data.model.GetFilmsResponse

interface FilmRepository {
    suspend fun getFilms(): GetFilmsResponse
    suspend fun getLocalFilms(): GetFilmsResponse
}
