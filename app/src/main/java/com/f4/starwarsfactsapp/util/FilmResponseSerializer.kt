package com.f4.starwarsfactsapp.util

import androidx.datastore.core.Serializer
import com.f4.starwarsfactsapp.data.model.GetFilmsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object FilmResponseSerializer : Serializer<GetFilmsResponse> {
    override val defaultValue: GetFilmsResponse
        get() = GetFilmsResponse()

    override suspend fun readFrom(input: InputStream): GetFilmsResponse {
        return try {
            Gson().fromJson(input.readBytes().decodeToString(), GetFilmsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: GetFilmsResponse, output: OutputStream) {
        val jsonString = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray(StandardCharsets.UTF_8))
        }
    }
}