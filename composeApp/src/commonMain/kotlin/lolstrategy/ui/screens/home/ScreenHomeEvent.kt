package lolstrategy.ui.screens.home

sealed interface ScreenHomeEvent {
    data object GoToMatchupAnalyzerScreen : ScreenHomeEvent
    data class UpdateUserChampion(val userChampion: String) : ScreenHomeEvent
    data class UpdateEnemyChampion(val enemyChampion: String) : ScreenHomeEvent
    data object GoBack : ScreenHomeEvent
}
