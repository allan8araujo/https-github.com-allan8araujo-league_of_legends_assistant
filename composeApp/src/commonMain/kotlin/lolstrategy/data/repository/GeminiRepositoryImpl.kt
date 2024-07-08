package lolstrategy.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import lolstrategy.data.models.GeminiContentRequest
import lolstrategy.data.models.GeminiContentResponse
import lolstrategy.domain.repository.GeminiRepository

private const val BASE_URL = " https://generativelanguage.googleapis.com/v1beta/models"
private const val API_KEY = ""

internal class GeminiRepositoryImpl(private val client: HttpClient) : GeminiRepository {
    override suspend fun generateContent(request: GeminiContentRequest): GeminiContentResponse {
        return client.post("$BASE_URL/gemini-pro:generateContent") {
            contentType(ContentType.Application.Json)
            url { parameters.append("key", API_KEY) }
            setBody(request)
        }.body<GeminiContentResponse>()
    }
}
