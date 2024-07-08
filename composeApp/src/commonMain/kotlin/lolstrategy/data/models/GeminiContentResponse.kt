package lolstrategy.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiContentResponse(
    val error: Error? = null,
    val candidates: List<Candidate>? = null
)

@Serializable
data class Candidate(val content: Content)

@Serializable
data class Error(val message: String)
