package lolstrategy.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiContentRequest(
    val contents: Content,
)

@Serializable
data class Content(val parts: List<Part>)

@Serializable
data class Part(val text: String)
