package lolstrategy.domain.models

sealed class GeminiState {
    data object Loading : GeminiState()
    data class Success(val content: String) : GeminiState()
    data class Error(val message: String) : GeminiState()
}


internal fun String.filterContent() = this.replace("**", "")
