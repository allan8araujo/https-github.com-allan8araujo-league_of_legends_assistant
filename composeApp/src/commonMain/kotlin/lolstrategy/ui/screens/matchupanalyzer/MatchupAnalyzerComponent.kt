package lolstrategy.ui.screens.matchupanalyzer

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import lolstrategy.domain.models.GeminiState
import lolstrategy.domain.usecase.GeminiUseCase
import lolstrategy.ui.common.MatchupState
import lolstrategy.ui.common.toComposableText

class MatchupAnalyzerComponent(
    private val getGeminiUseCase: GeminiUseCase,
    private val goToMatchupAnalyzer: () -> Unit,
    val goBack: () -> Unit,
    matchupState: MatchupState?,
    componentContext: ComponentContext,
) {

    private var _userChampion = MutableValue(matchupState?.userChampion ?: "")
    val userChampion: Value<String> = _userChampion

    private var _enemyChampion = MutableValue(matchupState?.enemyChampion ?: "")
    val enemyChampion: Value<String> = _enemyChampion

    private val _matchupAnalyzerState = MutableStateFlow<GeminiState>(GeminiState.Loading)
    val matchupAnalyzerState: MutableStateFlow<GeminiState> = _matchupAnalyzerState

    suspend fun getAnalyzeMatchup() {

        getGeminiUseCase.getAnalyzeMatchup(getPrompt()).collect { result ->
            when (result) {
                GeminiState.Loading -> _matchupAnalyzerState.emit(GeminiState.Loading)
                is GeminiState.Error -> _matchupAnalyzerState.emit(
                    GeminiState.Error(result.message)
                )

                is GeminiState.Success -> _matchupAnalyzerState.emit(
                    GeminiState.Success(
                        content = result.content.toComposableText().toString()
                    )
                )
            }
        }
    }

    private fun getPrompt() =
        "i'm on a league of legends ranked solo queue, how can i play matchup of ${_userChampion.value} against ${_enemyChampion.value} on mid? please restrictively meet this template: 1 - Early game levels 1-8. 2 - Mid game levels 8-12. 3 - Late game levels 12-18. 4 - Bonus Tip:"
}
