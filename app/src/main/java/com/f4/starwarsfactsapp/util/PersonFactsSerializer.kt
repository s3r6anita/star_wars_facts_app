package com.f4.starwarsfactsapp.util

import androidx.datastore.core.Serializer
import com.f4.starwarsfactsapp.data.model.GetPersonsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object PersonResponseSerializer : Serializer<GetPersonsResponse> {
    override val defaultValue: GetPersonsResponse
        get() = GetPersonsResponse()

    override suspend fun readFrom(input: InputStream): GetPersonsResponse {
        return try {
            Gson().fromJson(input.readBytes().decodeToString(), GetPersonsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: GetPersonsResponse, output: OutputStream) {
        val jsonString = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray(StandardCharsets.UTF_8))
        }
    }
}
