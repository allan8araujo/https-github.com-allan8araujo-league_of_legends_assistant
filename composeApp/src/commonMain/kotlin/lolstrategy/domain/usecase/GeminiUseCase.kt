package lolstrategy.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import lolstrategy.data.models.Content
import lolstrategy.data.models.GeminiContentRequest
import lolstrategy.data.models.Part
import lolstrategy.domain.models.GeminiState
import lolstrategy.domain.repository.GeminiRepository

class GeminiUseCase(private val geminiRepository: GeminiRepository) {
    suspend fun getAnalyzeMatchup(prompt: String): Flow<GeminiState> =
        flow {
            emit(GeminiState.Loading)
            val request = GeminiContentRequest(
                contents = Content(
                    parts = listOf(
                        Part(
                            text = prompt
                        )
                    )
                ),
            )

            emit(
                GeminiState.Success(
                    geminiRepository.generateContent(request)
                        .candidates?.get(0)?.content?.parts?.get(
                            0
                        )?.text ?: "no results"
                )
            )
        }.catch {
            GeminiState.Error(message = it.message ?: "")
        }
}
