package lolstrategy.domain.repository

import lolstrategy.data.models.GeminiContentRequest
import lolstrategy.data.models.GeminiContentResponse

interface GeminiRepository {
    suspend fun generateContent(request: GeminiContentRequest): GeminiContentResponse
}
