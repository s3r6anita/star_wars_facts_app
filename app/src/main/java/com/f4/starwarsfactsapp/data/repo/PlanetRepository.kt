package com.f4.starwarsfactsapp.data.repo

import com.f4.starwarsfactsapp.data.model.GetPlanetsResponse

interface PlanetRepository {
    suspend fun getPlanets(): GetPlanetsResponse
    suspend fun getLocalPlanets(): GetPlanetsResponse
}