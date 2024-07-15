package com.f4.starwarsfactsapp.util

import androidx.datastore.core.Serializer
import com.f4.starwarsfactsapp.data.model.GetPlanetsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object PlanetResponseSerializer : Serializer<GetPlanetsResponse> {
    override val defaultValue: GetPlanetsResponse
        get() = GetPlanetsResponse()

    override suspend fun readFrom(input: InputStream): GetPlanetsResponse {
        return try {
            Gson().fromJson(input.readBytes().decodeToString(), GetPlanetsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: GetPlanetsResponse, output: OutputStream) {
        val jsonString = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray(StandardCharsets.UTF_8))
        }
    }
}
