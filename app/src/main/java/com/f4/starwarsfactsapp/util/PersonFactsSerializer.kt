package com.f4.starwarsfactsapp.util

import androidx.datastore.core.Serializer
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object PersonsFactsResponseSerializer : Serializer<PersonsFactsResponse> {
    override val defaultValue: PersonsFactsResponse
        get() = PersonsFactsResponse()

    override suspend fun readFrom(input: InputStream): PersonsFactsResponse {
        return try {
            Gson().fromJson(input.readBytes().decodeToString(), PersonsFactsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: PersonsFactsResponse, output: OutputStream) {
        val jsonString = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray(StandardCharsets.UTF_8))
        }
    }
}
