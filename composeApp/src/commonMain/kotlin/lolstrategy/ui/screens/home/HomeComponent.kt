package lolstrategy.ui.screens.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import lolstrategy.ui.common.MatchupState

class HomeComponent(
    private val goToMatchupAnalyzer: (state: MatchupState) -> Unit,
    val goBack: () -> Unit,
    componentContext: ComponentContext,
) {

    private val stateKeeper = StateKeeperDispatcher()

    private var _userChampion = MutableValue("")
    val userChampion: Value<String> = _userChampion

    private var _enemyChampion = MutableValue("")
    val enemyChampion: Value<String> = _enemyChampion

    private var state =
        stateKeeper.consume(
            key = matchup_state,
            strategy = MatchupState.serializer()
        ) ?: getAnalyzeMatchupState()

    init {
        stateKeeper.register(
            key = matchup_state,
            strategy = MatchupState.serializer()
        ) {
            state
        }
    }

    fun getAnalyzeMatchupState() = MatchupState(
        userChampion = _userChampion.value,
        enemyChampion = _enemyChampion.value
    )

    fun onEvent(event: ScreenHomeEvent) {
        when (event) {

            ScreenHomeEvent.GoToMatchupAnalyzerScreen -> {
                goToMatchupAnalyzer(getAnalyzeMatchupState())
            }

            is ScreenHomeEvent.UpdateUserChampion -> {
                _userChampion.value = event.userChampion
            }

            is ScreenHomeEvent.UpdateEnemyChampion -> {
                _enemyChampion.value = event.enemyChampion
            }

            is ScreenHomeEvent.GoBack -> {
                goBack()
            }
        }
    }

    companion object {
        const val matchup_state = "MATCHUP_STATE"
    }

}
